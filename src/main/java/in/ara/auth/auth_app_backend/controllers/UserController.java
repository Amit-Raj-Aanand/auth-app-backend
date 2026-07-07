package in.ara.auth.auth_app_backend.controllers;

import in.ara.auth.auth_app_backend.dto.UserDto;
import in.ara.auth.auth_app_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController{

    private final UserService userService;

    //API: create user
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) throws IllegalAccessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    //API: get all user
    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getAllUser(){
        return  ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    //API: get user by emailId
    @GetMapping("/email/{emailId}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("emailId") String email){
        return ResponseEntity.ok(userService.getUserByEmailId(email));
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") String userId){
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userDto, userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }

}
