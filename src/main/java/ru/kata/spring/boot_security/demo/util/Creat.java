package ru.kata.spring.boot_security.demo.util;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class Creat implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;


    public Creat(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public void run(String... args) {
        Role ROLE_USER = new Role("ROLE_USER");
        roleService.addRole(ROLE_USER);
        Role ROLE_ADMIN = new Role("ROLE_ADMIN");
        roleService.addRole(ROLE_ADMIN);

        User admin = new User();
        admin.setName("Владимир");
        admin.setLastName("Ульянов");

        admin.setUsername("admin"); // <----------------------
        admin.setUserpassword("1234");

        admin.setRoles(new HashSet<>(Arrays.asList(ROLE_USER, ROLE_ADMIN)));
        userService.addUser(admin);

        User user = new User();
        user.setName("User");
        user.setLastName("User");

        user.setUsername("user"); // <----------------------
        user.setUserpassword("1234");

        user.setRoles(new HashSet<>(List.of(ROLE_USER)));
        userService.addUser(user);

    }

}

