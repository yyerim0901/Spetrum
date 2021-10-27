package com.spectrum.service;

import com.spectrum.common.request.UserRegisterPostReq;
import com.spectrum.entity.user;
import com.spectrum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(UserRegisterPostReq registerInfo, MultipartFile thumbnail) {
        user res = new user();
        res.setUserId(registerInfo.getUserId());
        res.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
        res.setNickname(registerInfo.getNickname());

        userRepository.save(res);
    }
}
