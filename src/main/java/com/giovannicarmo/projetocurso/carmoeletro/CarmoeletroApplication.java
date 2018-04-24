package com.giovannicarmo.projetocurso.carmoeletro;

import com.giovannicarmo.projetocurso.carmoeletro.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarmoeletroApplication implements CommandLineRunner {

    @Autowired
    private S3Service s3Service;

    public static void main(String[] args) {
        SpringApplication.run(CarmoeletroApplication.class, args);
    }

    @Override
    public void run(String... args) {
        s3Service.uploadFile("C:\\Users\\giova\\OneDrive\\Imagens\\Imagens salvas\\God-Of-War2.jpg");
    }
}
