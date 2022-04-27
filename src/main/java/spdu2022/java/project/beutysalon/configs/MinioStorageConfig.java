package spdu2022.java.project.beutysalon.configs;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("minio")
@PropertySource("classpath:minio.properties")
public class MinioStorageConfig {
    @Value("${minio.end-point}")
    private String endpoint;
    @Value("${minio.port}")
    private Integer port;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Value("${minio.secure}")
    private boolean secure;
    private long imageSize;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .credentials(accessKey, secretKey)
                .endpoint(endpoint,port,secure)
                .build();
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Integer getPort() {
        return port;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public boolean isSecure() {
        return secure;
    }

    public long getImageSize() {
        return imageSize;
    }

    public void setImageSize(long imageSize) {
        this.imageSize = imageSize;
    }

}
