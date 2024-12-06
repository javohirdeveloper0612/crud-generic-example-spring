package uz.javadev.crudgenericexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrudGenericExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudGenericExampleApplication.class, args);
    }

}
