package in.ara.auth.auth_app_backend.services.Impl;

import in.ara.auth.auth_app_backend.dto.UserDto;
import in.ara.auth.auth_app_backend.entities.Provider;
import in.ara.auth.auth_app_backend.entities.User;
import in.ara.auth.auth_app_backend.exceptions.ResourceNotFoundException;
import in.ara.auth.auth_app_backend.helpers.UserHelper;
import in.ara.auth.auth_app_backend.repositories.UserRepo;
import in.ara.auth.auth_app_backend.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto){
        if(userDto.getEmail() == null || userDto.getEmail().isBlank()){
            throw new IllegalArgumentException("email is required!");
        }
        if(userRepo.existsByEmail(userDto.getEmail())){
            throw new IllegalArgumentException("email already present!");
        }

        // if u have extra checks...put it here
        //TODO: ________

        // convert userDto to user entity using ModelMapper
        User user = modelMapper.map(userDto, User.class);
        user.setProvider(userDto.getProvider() != null ? userDto.getProvider() : Provider.LOCAL);
        //role assign here to user...for authorization
        //TODO: Authorization

        User savedUser = userRepo.save(user);

        //convert user to savedUser to UserDTO
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserByEmailId(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("user not found with given email id"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        UUID uuid = UserHelper.parseUuid(userId);
        User user = userRepo.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("user not found with given id"));
        if(userDto.getEmail() != null) user.setEmail(userDto.getEmail());
        if(userDto.getName() != null) user.setName(userDto.getName());
        if(userDto.getImage() != null) user.setImage(userDto.getImage());
        if(userDto.getPassword() != null) user.setPassword(userDto.getPassword());
        //TODO: password undation logic
        if(userDto.getProvider() != null) user.setProvider(userDto.getProvider());
        user.setEnable(userDto.isEnable());
        User updatedUser = userRepo.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        UUID uuid = UserHelper.parseUuid(userId);
        User user = userRepo.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("resource not found exception!"));
        userRepo.delete(user);
    }

    @Override
    public UserDto getUserById(String userId) {
        UUID uuid = UserHelper.parseUuid(userId);
        User user = userRepo.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("user not found with given id"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public Iterable<UserDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(user -> modelMapper
                        .map(user, UserDto.class))
                .toList();
    }
}
