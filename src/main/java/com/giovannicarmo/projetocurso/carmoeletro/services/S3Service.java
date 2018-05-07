package com.giovannicarmo.projetocurso.carmoeletro.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.giovannicarmo.projetocurso.carmoeletro.services.exception.FileException;
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
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream inputStream = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();

            return uploadFile(inputStream, fileName, contentType);

        } catch (IOException e) {
            throw new FileException("IO error: " + e.getMessage());
        }
    }

    public URI uploadFile(InputStream inputStream, String fileName, String contentType){
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            LOG.info("Upload initiated.");
            s3Client.putObject(bucketName, fileName, inputStream, metadata);
            LOG.info("Upload finished");

            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Error on URL to URI convertion");
        }
    }
}
