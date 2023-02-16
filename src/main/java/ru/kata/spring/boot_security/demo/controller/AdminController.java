package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

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
    public String getAdminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/adminPanel";
    }
    @GetMapping("/createUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "admin/newUser";
    }

    @PostMapping("/createNewUser")
    public String createUser(@ModelAttribute("user") User user) {
        List<Role> listRoles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            listRoles.add(roleService.getRoleByName(role.getName()));
        }
        user.setRoles(listRoles);

        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "admin/editUser";
    }

    @PatchMapping("/editUser/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") long id) {
        List<Role> listRoles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            listRoles.add(roleService.getRoleByName(role.getName()));
        }
        user.setRoles(listRoles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        System.out.println("stop");
        userService.deleteUserById(userService.getUserById(id));
        return "redirect:/admin";
    }


}
