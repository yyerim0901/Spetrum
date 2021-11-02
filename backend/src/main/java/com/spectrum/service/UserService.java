package com.spectrum.service;

import com.spectrum.common.request.UserRegisterPostReq;
import com.spectrum.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    void createUser(UserRegisterPostReq registerInfo, MultipartFile thumbnail);

    User findUserByUserId(String userId);

    User search(String userid);

    User searchNick(String usernickname);
}
