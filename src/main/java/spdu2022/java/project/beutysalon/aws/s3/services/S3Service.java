package spdu2022.java.project.beutysalon.aws.s3.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class S3Service {
    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    private final AmazonS3 s3Client;
    private final String STAFF_AVATAR_FILE_NAME_TEMPLATE = "staff_%d_avatar_%s";

    public S3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(long staffId, MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String originalFileName = file.getOriginalFilename();
        String fileName = String.format(STAFF_AVATAR_FILE_NAME_TEMPLATE, staffId, originalFileName);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        boolean deleted = fileObj.delete();
        return deleted ?
                String.format("File uploaded: %s", fileName) :
                String.format("File wasn't uploaded: %s", fileName);
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to handle byte input stream.");
        }
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    public String createBucket(String bucketName) {
        s3Client.createBucket(bucketName);
        return bucketName + " was created...";
    }

    public String createFolder(String folderName) {
        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        s3Client.putObject(new PutObjectRequest(bucketName, folderName + "/", inputStream, metadata));
        return folderName + " was created";
    }

    public String deleteFolder(String folderName) {
        final List<S3ObjectSummary> fileList = s3Client.listObjects(bucketName, folderName).getObjectSummaries();
        fileList.forEach(file -> s3Client.deleteObject(bucketName, file.getKey()));
        s3Client.deleteObject(bucketName, folderName);
        return folderName + " was removed";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        final String fileName = file.getOriginalFilename();
        final File convertedFile = new File(fileName != null ? fileName : "test.txt");
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
