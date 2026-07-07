package in.ara.auth.auth_app_backend.services;

import in.ara.auth.auth_app_backend.dto.UserDto;

public interface UserService {

    //create user
    UserDto createUser(UserDto userDto);

    //get user by email
    UserDto getUserByEmailId(String email);

    //update user
    UserDto updateUser(UserDto userDto, String UserId);

    //delete user
    void deleteUser(String userId);

    //get user by id
    UserDto getUserById(String userId);

    //getAll user
    Iterable<UserDto> getAllUsers();
}
