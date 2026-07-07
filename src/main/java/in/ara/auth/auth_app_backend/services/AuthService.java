package in.ara.auth.auth_app_backend.services;

import in.ara.auth.auth_app_backend.dto.UserDto;

public interface AuthService {
    UserDto registerUser(UserDto userDto);
}
