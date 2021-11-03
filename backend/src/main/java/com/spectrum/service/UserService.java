package com.spectrum.service;

import com.spectrum.common.request.UserRegisterPostReq;
import com.spectrum.common.request.UserUpdateReq;
import com.spectrum.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    void createUser(UserRegisterPostReq registerInfo) throws IOException;

    User findUserByUserId(String userId);

    User search(String userid);

    User searchNick(String usernickname);

    void updateUser(UserUpdateReq updateInfo, String userid) throws IOException;
}
