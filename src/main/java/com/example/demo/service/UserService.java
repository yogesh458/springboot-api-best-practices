package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserReturn;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public Page<UserReturn> getAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size))
                .map(user -> new UserReturn(user.getId(), user.getName()));
    }

    public UserReturn addUser(UserRequest request){
        User user =new User();
        user.setName(request.name());
        user.setPassword(request.password());
        User save=userRepository.save(user);
        return new UserReturn(save.getId(),save.getName());
    }

    public UserReturn getUserById(Long id){
    return userRepository.findById(id)
        .map(user -> new UserReturn(user.getId(), user.getName()))
        .orElseThrow(() -> new UserNotFoundException(id));
}


}
