package spdu2022.java.project.beutysalon.file_storage.services;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spdu2022.java.project.beutysalon.configs.MinioStorageConfig;
import spdu2022.java.project.beutysalon.exeptions.FileStorageException;
import spdu2022.java.project.beutysalon.file_storage.FileStorageServices;

@Service
@Profile("minio")
public class MinioServices implements FileStorageServices {
    @Value("${file-storage.bucket-name}")
    private String bucketName;

    private final MinioClient minioClient;
    private final MinioStorageConfig minioStorageConfig;

    public MinioServices(MinioClient minioClient, MinioStorageConfig minioStorageConfig) {
        this.minioClient = minioClient;
        this.minioStorageConfig = minioStorageConfig;
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
        return new byte[0];
    }

    @Override
    public String deleteFile(String fileName) {
        return "Delete file";
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

}
