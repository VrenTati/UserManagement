package org.example.usermanagement.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.usermanagement.data.User;
import org.example.usermanagement.exeption.UserNotFoundException;
import org.example.usermanagement.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping("/all_users")
    public List<User> showUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return user.get().toString();
        } else {
            return new UserNotFoundException("User with id: " + id + " did`nt found").toString();
        }
    }

    @PostMapping("/add_user")
    public String addUser(@RequestParam String name,
                          @RequestParam String lastname,
                          @RequestParam String login,
                          @RequestParam String password,
                          @RequestParam int age) {
        System.out.println("Received request to add user");
        User newUser = userService.addUser(name, lastname, age, login, password);
        return newUser.toString();
    }

    @DeleteMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id).toString();
    }

    @PutMapping("/change_user/{id}")
    public String changeUser(@PathVariable int id,
                             @RequestParam String login,
                             @RequestParam String password) {

        return userService.changeUser(id, login, password);
    }
}
