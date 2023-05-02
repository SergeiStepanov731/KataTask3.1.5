package ru.kata.spring.boot_security.demo.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/")
public class MainRestController {

    private final ResourceLoader resourceLoader;

    public MainRestController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping
    public ResponseEntity<Void> mainPage() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/login")
                .build();
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginPage() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:templates/login.html");
        String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok(html);
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminPage() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:templates/admin.html");
        String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok(html);
    }

    @GetMapping("/user")
    public ResponseEntity<String> userPage() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:templates/user.html");
        String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok(html);
    }

}



