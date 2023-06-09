package spdu2022.java.project.beutysalon.filestorage;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageServices {
    boolean saveFile(String fileName, MultipartFile file);

    byte[] downloadFile(String fileName);

    void deleteFile(String fileName);

    void makeBucket(String bucketName);
}