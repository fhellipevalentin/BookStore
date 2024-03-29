package io.github.fhellipe.bookstore.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.github.fhellipe.bookstore.services.exceptions.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            return uploadFile(is, fileName, contentType);
        } catch (IOException e) {
            throw new FileException("Erro de IO: " + e.getMessage());
        }
    }

    public URI uploadFile(InputStream is, String localFilePath, String contentType) {

        try {
            LOG.info("Iniciando upload");
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            s3client.putObject(
                    new PutObjectRequest(bucketName, localFilePath, is, metadata)
            );
            LOG.info("Upload finalizado");
            return s3client.getUrl(bucketName, localFilePath).toURI();
        }
        catch (URISyntaxException e) {
            throw new FileException("Erro ao converter URL para URI");
        }
    }
}
