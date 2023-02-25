package ru.kata.spring.boot_security.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAdminPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("newUser", new User());
        return "adminPage";
    }

    @GetMapping("/userList")
    @ResponseBody
    public ResponseEntity<List<User>> getUserList() {
        System.out.println("we were here!");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/createNewUser")
    @ResponseBody
    public ResponseEntity<String> createUser(@RequestBody User user) {
        List<Role> listRoles = new ArrayList<>();
        System.out.println("add new");
        System.out.println(user.toString());
        for (Role role : user.getRoles()) {
            listRoles.add(roleService.getRoleByName(role.getName()));
        }
        user.setRoles(listRoles);
        userService.addUser(user);
        return ResponseEntity.ok("");
    }

    @PatchMapping("/editUser")
    @ResponseBody
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        System.out.println("update");
        System.out.println(user.toString());
        List<Role> listRoles = new ArrayList<>();
        user.getRoles().forEach(System.out::println);
        for (Role role : user.getRoles()) {
            listRoles.add(roleService.getRoleByName(role.getName()));
        }
        user.setRoles(listRoles);
        userService.updateUser(user);
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/deleteUser/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }


}
