package com.waoss.lavadro;

import com.waoss.lavadro.model.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LavadroApplication {

    private final static Logger log = LoggerFactory.getLogger(LavadroApplication.class);
    private final UserRepository userRepository;

    @Autowired
    public LavadroApplication(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LavadroApplication.class, args);
    }


}
