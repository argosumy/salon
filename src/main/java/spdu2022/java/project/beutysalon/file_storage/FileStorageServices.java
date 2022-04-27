package spdu2022.java.project.beutysalon.file_storage;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageServices {
    boolean saveFile(String fileName, MultipartFile file);
    byte[] downloadFile(String fileName);
    String deleteFile(String fileName);
    void makeBucket(String bucketName);
}
