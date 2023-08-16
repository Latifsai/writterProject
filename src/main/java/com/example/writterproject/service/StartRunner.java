package com.example.writterproject.service;

import com.example.writterproject.config.DatabaseConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@Slf4j
public class StartRunner implements CommandLineRunner {
   @Autowired
   private DatabaseConfig config;

    @Override
    public void run(String... args) throws Exception {
        log.info("Load role into database table ...");
        config.fillRoleTable();
        log.info("Done...");
    }
}
