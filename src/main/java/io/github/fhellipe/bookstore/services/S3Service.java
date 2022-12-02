package io.github.fhellipe.bookstore.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    @Value("${aws.secret_access_key}")
    private String keyName;

    public void uploadFile(String localFilePath) {
        File file = new File(localFilePath);
        LOG.info("Iniciando upload");
        try (final InputStream stream = new FileInputStream(file)) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.length());
            s3client.putObject(
                    new PutObjectRequest(bucketName, file.getName(), stream, metadata)
            );
            LOG.info("Upload finalizado");
        }
        catch (AmazonServiceException e) {
            LOG.info("AmazonServiceException: " + e.getErrorMessage());
            LOG.info("Status code: " + e.getErrorCode());
        }
        catch (AmazonClientException e) {
            LOG.info("AmazonClientException: " +  e.getMessage());
        }
        catch (IOException e) {
            LOG.info("IOExcpetion: " + e.getMessage());
        }
    }
}
