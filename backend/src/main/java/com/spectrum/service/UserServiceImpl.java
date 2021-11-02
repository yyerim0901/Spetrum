package com.spectrum.service;

import com.spectrum.common.request.UserRegisterPostReq;
import com.spectrum.entity.User;
import com.spectrum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(UserRegisterPostReq registerInfo, MultipartFile thumbnail) {
        User res = new User();
        res.setUserId(registerInfo.getUserId());
        res.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
        res.setNickname(registerInfo.getNickname());

        userRepository.save(res);
    }

    @Override
    public User findUserByUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);

        if(user.isPresent())
        {
            return user.get();
        }
        else {
            return null;
        }
    }

    @Override
    public User search(String userid) {
        Optional<User> user = userRepository.findByUserId(userid);
        if(user.isPresent())
            return user.get();
        else
            return null;
    }

    @Override
    public User searchNick(String usernickname) {
        Optional<User> user = userRepository.findByNickname(usernickname);
        if(user.isPresent())
            return user.get();
        else
            return null;
    }
}
