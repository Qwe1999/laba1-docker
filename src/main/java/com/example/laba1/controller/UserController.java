package com.example.laba1.controller;

import com.example.laba1.model.User;
import com.example.laba1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public List<User> getAllEmployees() {

        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") long employeeId) {
        User user = repository.findById(employeeId).
                orElseThrow(() -> new RuntimeException("Not found"));
        return ResponseEntity.ok().body(user);

    }

    @PostMapping("/users")
    public User createEmployee(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> EmployeeById(@PathVariable(value = "id") long employeeId, @RequestBody User updatedEmployee)  {
        User user = repository.findById(employeeId).
                orElseThrow(() -> new RuntimeException("Not found"));
        user.setName(updatedEmployee.getName());
        user.setAge(updatedEmployee.getAge());
        repository.save(user);
        return ResponseEntity.ok().body(user);

    }

    @DeleteMapping("/users/{id}")
    public void deleteEmployee(@PathVariable(value = "id") long employeeId)  {
        User user = repository.findById(employeeId).
                orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(user);
    }
}
