package com.PractionBE.service;

import com.PractionBE.domain.User;
import com.PractionBE.domain.repository.UserRepository;
import com.PractionBE.dtos.reponse.UserResponse;
import com.PractionBE.dtos.request.UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse saveUser(UserRequest userRequest){
        User user = userRepository.save(new User(
                userRequest.getEmail(),
                userRequest.getName(),
                userRequest.getPassword(),
                userRequest.getNickName()
                ));
        return createUserResponse(user);
    }

    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id).get();
        return createUserResponse(user);
    }

    public List<UserResponse> findAllUsers() {
        return userRepository.findAll().stream()
                .map(this::createUserResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUserById(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).get();
        user.updateUser(userRequest.getPassword(), userRequest.getNickName());
    }

    private UserResponse createUserResponse(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getName(), user.getNickName());
    }

}
