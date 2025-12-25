package com.swsisolutions.realtimeRestApi.rest;

import com.swsisolutions.realtimeRestApi.entity.User;
import com.swsisolutions.realtimeRestApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private UserService userService;
    private ObjectMapper objectMapper;

    @Autowired
    public UserRestController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("user/{id}")
    public Optional<User> findUser(@PathVariable int id) {
        Optional<User> employee = userService.findUser(id);
        if (employee.isEmpty()) {
            throw  new RuntimeException("User Id Not Found");
        } else {
            return employee;
        }
    }

    @PostMapping("user")
    public User addUser(@RequestBody User employee) {
        if (employee.getId() == 0) {
            employee.setId(0);
        }
        return userService.addUser(employee);
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PatchMapping("/user/{employeeId}")
    public User patchUser(@PathVariable int employeeId,
                                  @RequestBody Map<String, Object> patchPayload) {
        Optional<User> tempUser = userService.findUser(employeeId);
        // throw exception if null
        if (tempUser.isEmpty()) {
            throw new RuntimeException("User id not found - " + employeeId);
        }

        // throw exception if request body contains "id" key
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("User id not allowed in request body - " + employeeId);
        }

        User patchedUser = apply(patchPayload, tempUser);

        User dbUser = userService.addUser(patchedUser);

        return dbUser;

    }

    private User apply(Map<String, Object> patchPayload, Optional<User> tempUser) {

        // Convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempUser, ObjectNode.class);

        // Convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        // Merge the patch updates into the employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, User.class);
    }

}
