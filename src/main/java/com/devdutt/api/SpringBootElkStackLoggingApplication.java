package com.devdutt.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class SpringBootElkStackLoggingApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootElkStackLoggingApplication.class);

    @GetMapping("/getUser/{id}")
    public UserEntity getUserById(@PathVariable int id) {
        List<UserEntity> userList = getUser();
        UserEntity user = userList.stream().filter(u -> u.getId() == id).findAny().orElse(null);
        if (user != null) {
            logger.info("user found :{}", user);
            return user;
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("User Not Found with ID : {}", id);
            }
        }
        return user;
    }

    private List<UserEntity> getUser() {
        return Stream.of(new UserEntity(1, "John"),
                        new UserEntity(2, "Shyam"),
                        new UserEntity(3, "Rony"),
                        new UserEntity(4, "mak"))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        SpringApplication.run(SpringBootElkStackLoggingApplication.class, args);
    }

}
