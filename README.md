[![pipeline status](https://gitlab.com/argosumy/java/badges/pipeline/pipeline.svg)](https://gitlab.com/argosumy/java/-/commits/pipeline)
[![coverage report](https://gitlab.com/argosumy/java/badges/pipeline/coverage.svg)](https://gitlab.com/argosumy/java/-/commits/pipeline)

#Project SPD-Ukraine Beauty-salon


#Run project in docker
APP  host: http://localhost:
     port: 8081
     path: /swagger-ui/

MAIL HOG 
     host: http://localhost:
     port: 8025
       
1. MINIO or AWS storage select config properties:
     property file path: src/main/resources/application.properties
     property for MINIO: minio
     property for AWS S3: aws
     
     EXAMPLE:
     spring.profiles.active=aws, dev
    
1.2. For AWS S3 select credentials:
     property file path: src/main/resources/aws.properties
     
     EXAMPLE:
     aws.s3.credentials.access-key=****************
     aws.s3.credentials.secret-key=****************
     aws.s3.region=us-east-1
    
1.3 For MINIO: Start storage.
     docker compose path: .local/log-directory/minio/docker-compose-minio.yml
     command: docker-compose -f .local/log-directory/minio/docker-compose-minio.yml up

2.   Start MAIL HOG
     docker compose path:.local/log-directory/mail-hog/docker-compose-mail-hog.yml
     command: docker-compose -f .local/log-directory/mail-hog/docker-compose-mail-hog.yml up
     
4.   Start App + DB.
     docker compose path: .local/docker-compose.yml    
     command: docker-compose -f .local/docker-compose.yml up

#Gradle run project 
APP  host: http://localhost:
     port: 8080
     path: /swagger-ui/

MAIL HOG 
     host: http://localhost:
     port: 8025
     
5. Step 1, 2, 3

6. Copy project from gitlab 
     command:git clone https://gitlab.com/argosumy/java.git 

7. Build jar file.
     command:.\gradlew build
8.  