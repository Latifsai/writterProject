package com.example.writterproject.config;

import com.example.writterproject.domain.Role;
import com.example.writterproject.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class DatabaseConfig {

    private final RoleRepository repository;

    public DatabaseConfig(RoleRepository repository) {
        this.repository = repository;
    }

    @Bean
    public void fillRoleTable() {
        Role admin = new Role();
        admin.setName("ADMIN");
        repository.save(admin);


        Role user = new Role();
        user.setName("USER");
        repository.save(user);
    }
}
