package spdu2022.java.project.beutysalon.file_storage.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spdu2022.java.project.beutysalon.file_storage.FileStorageServices;

import java.io.*;
import java.util.List;

@Service
@Profile("aws")
public class AwsS3Services implements FileStorageServices {
    @Value("${file-storage.bucket-name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public AwsS3Services(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public boolean saveFile(String fileName, MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        return fileObj.delete();
    }

    @Override
    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to handle byte input stream.");
        }
    }

    @Override
    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    @Override
    public void makeBucket(String bucketName) {
        s3Client.createBucket(bucketName);
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
