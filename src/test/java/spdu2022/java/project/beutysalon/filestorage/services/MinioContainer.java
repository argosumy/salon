package spdu2022.java.project.beutysalon.filestorage.services;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.utility.Base58;

import java.time.Duration;

public class MinioContainer extends GenericContainer<MinioContainer> {
    private static final int PORT = 9000;
    private static final String IMAGE = "minio/minio:latest";

    private static final String MINIO_ACCESS_KEY = "MINIO_ACCESS_KEY";
    private static final String MINIO_SECRET_KEY = "MINIO_SECRET_KEY";

    private static final String DEFAULT_STORAGE_DIRECTORY = "/data/";
    private static final String HEALTH_ENDPOINT = "/minio/health/ready";

    public MinioContainer(String accessKey, String secretKey) {
        this(IMAGE, accessKey, secretKey);
    }

    public MinioContainer(String image, String accessKey, String secretKey) {
        super(image);
        withNetworkAliases("minio-" + Base58.randomString(6));
        addExposedPort(PORT);

        withEnv(MINIO_ACCESS_KEY, accessKey);
        withEnv(MINIO_SECRET_KEY, secretKey);

        withCommand("server", DEFAULT_STORAGE_DIRECTORY);
        setWaitStrategy(new HttpWaitStrategy()
                .forPort(PORT)
                .forPath(HEALTH_ENDPOINT)
                .withStartupTimeout(Duration.ofMinutes(2)));
    }

    public String getUrlAddress() {
        return "http://" + getContainerIpAddress() + ":" + getMappedPort(PORT);
    }

}
