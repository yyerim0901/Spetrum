package com.spectrum.service;

import com.spectrum.common.request.UserRegisterPostReq;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    void createUser(UserRegisterPostReq registerInfo, MultipartFile thumbnail);
}
