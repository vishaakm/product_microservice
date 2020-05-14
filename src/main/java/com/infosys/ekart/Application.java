package com.infosys.ekart;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;


@SpringBootApplication
public class Application {
    public static Properties PROP;
    public static void main(String[] args) {
        PROP = readPropertiesFromS3();
        SpringApplication.run(Application.class, args);
    }

    public static Properties readPropertiesFromS3() {

        String key_name = "application.properties";
        String bucket_name = "properties-bucket";
        Properties prop = new Properties();

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion("us-east-2").build();
        S3Object object = s3.getObject(new GetObjectRequest(bucket_name, key_name));
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrOfStr = line.split("=");
                prop.put(arrOfStr[0].trim(), arrOfStr[1].trim());
            }

            object.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prop;

    }

}
