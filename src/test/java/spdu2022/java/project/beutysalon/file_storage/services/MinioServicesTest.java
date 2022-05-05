package spdu2022.java.project.beutysalon.file_storage.services;

import com.amazonaws.util.IOUtils;
import io.minio.BucketExistsArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MinioServicesTest {
    private static final String ACCESS_KEY = "accessKey";
    private static final String SECRET_KEY = "secretKey";
    private static final String BUCKET = "my-bucket";

    private static MinioContainer container;
    private static MinioServices minioServices;
    private static MinioClient client;

    private final String fileName = "file_name";

    @BeforeAll
    public static void startContainer() {
        container = new MinioContainer(ACCESS_KEY, SECRET_KEY);
        container.start();
        client =  getClient(container);
        minioServices = new MinioServices(client);
        minioServices.setBucketName(BUCKET);
        minioServices.makeBucket(BUCKET);
    }

    @AfterAll
    public static void shutDown() {
        if (container != null) {
            container.close();
        }
    }

    @Test
    @DisplayName("Method must upload file to the minio storage")
    void saveFile() throws IOException {
        int numRecordsBefore = numRecords();
        boolean result = minioServices.saveFile(fileName, getMultipartFile());
        int numRecordsAfter = numRecords();
        assertEquals(++numRecordsBefore, numRecordsAfter);
        assertTrue(result);
    }

    @Test
    @DisplayName("Method must delete file")
    void deleteFile() throws IOException {
        int numRecordsBefore = numRecords();
        int numRecordsAfter;
        if(numRecordsBefore == 1) {
            minioServices.deleteFile(fileName);
            numRecordsAfter = numRecords();
            assertEquals(0, numRecordsAfter);
        } else {
            minioServices.saveFile(fileName, getMultipartFile());
            numRecordsBefore = numRecords();
            minioServices.deleteFile(fileName);
            numRecordsAfter = numRecords();
            assertEquals(--numRecordsBefore,numRecordsAfter);
        }
    }

    @Test
    @DisplayName("Method must create bucket")
    void makeBucket() {
        minioServices.makeBucket("test");
        boolean result = false;
        try {
            result = client.bucketExists(BucketExistsArgs.builder().bucket("test").build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(result);
    }

    @Test
    @DisplayName("Method must download file")
    void downloadFile() throws IOException {
       if(numRecords() > 0) {
           byte[] content = minioServices.downloadFile(fileName);
           assertNotNull(content);
       } else {
           minioServices.saveFile(fileName, getMultipartFile());
           byte[] content = minioServices.downloadFile(fileName);
           assertNotNull(content);
       }
    }

    private static MinioClient getClient(MinioContainer container) {
        return MinioClient.builder()
                .endpoint(container.getUrlAddress())
                .credentials(ACCESS_KEY, SECRET_KEY)
                .httpClient(new OkHttpClient())
                .build();
    }

    private MultipartFile getMultipartFile() throws IOException {
        File file = new File("src/test/resources/file/avatar.jpg");
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile("file",
                file.getName(), "image/jpeg", IOUtils.toByteArray(input));
    }

    private int numRecords() {
        Iterable<Result<Item>> response = client.listObjects(ListObjectsArgs
                .builder()
                .bucket(BUCKET)
                .build());
        Iterator<Result<Item>> iterator = response.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        return count;
    }
}