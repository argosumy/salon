package spdu2022.java.project.beutysalon.file_storage.services;

import com.amazonaws.util.IOUtils;
import io.minio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spdu2022.java.project.beutysalon.exeptions.FileStorageException;
import spdu2022.java.project.beutysalon.file_storage.FileStorageServices;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Service
@Profile("minio")
public class MinioServices implements FileStorageServices {
    @Value("${file-storage.bucket-name}")
    private String bucketName;

    private final MinioClient minioClient;

    public MinioServices(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @PostConstruct
    private void init() {
        if(!bucketExists(bucketName)) {
            makeBucket(bucketName);
        }
    }

    @Override
    public boolean saveFile(String fileName, MultipartFile file) {
        try {
            minioClient.putObject(PutObjectArgs
                .builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .build());
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public byte[] downloadFile(String fileName) {
        try {
            InputStream obj = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build());

            byte[] content = IOUtils.toByteArray(obj);
            obj.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteFile(String fileName) {
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build();
        try {
            minioClient.removeObject(removeObjectArgs);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new FileStorageException(String.format("Error - file %s not deleted", fileName));
        }
    }

    @Override
    public void makeBucket(String bucketName) {
        if (bucketExists(bucketName)) {
            throw new FileStorageException(String.format("Bucket with name: %s already exists", bucketName));
        }
        try {
            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build();

            minioClient.makeBucket(makeBucketArgs);
        } catch (Exception e) {
            throw new FileStorageException(String.format("Cannot create bucket with name: %s", bucketName));
        }
    }

    private boolean bucketExists(String bucketName) {
        try {
            BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build();
            return minioClient.bucketExists(bucketExistsArgs);
        } catch (Exception e) {
            return false;
        }
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public MinioClient getMinioClient() {
        return minioClient;
    }
}
