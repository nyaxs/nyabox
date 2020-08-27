package com.nyaxs.nyabox;

import com.nyaxs.nyabox.entity.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileProperties.class
})
public class NyaboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(NyaboxApplication.class, args);
    }

}
