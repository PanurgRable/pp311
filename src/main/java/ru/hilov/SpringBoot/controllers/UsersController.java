package ru.hilov.SpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hilov.SpringBoot.model.User;
import ru.hilov.SpringBoot.servece.UserServece;


@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserServece userServece;
    @Autowired
    public UsersController(UserServece userServece) {
        this.userServece = userServece;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userServece.index());
        return "users/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("user",userServece.show(id));
        return "users/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user) {

        return "users/new";
    }
    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user){
        userServece.save(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userServece.delete(id);
        return "redirect:/users";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServece.show(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userServece.update(id, user);
        return "redirect:/users";
    }








}
