package com.spectrum.service;

import com.spectrum.common.request.UserRegisterPostReq;
import com.spectrum.common.request.UserUpdateReq;
import com.spectrum.entity.User;
import com.spectrum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    String BASE_PATH = new File("").getAbsolutePath() +"/src/main/resources/image/";

    @Override
    public void createUser(UserRegisterPostReq registerInfo, MultipartFile thumbnail) {
        User res = new User();
        res.setUserId(registerInfo.getUserId());
        res.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
        res.setNickname(registerInfo.getNickname());


        User tmpuser = userRepository.save(res);
        // sns 유저 폴더 생성
        String user_path = BASE_PATH + "sns/" + tmpuser.getId();
        File createFolder = new File(user_path);
        if(!createFolder.exists()) {
            createFolder.mkdirs();
        }
        createFolder.setWritable(true);
        createFolder.setReadable(true);
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

    @Override
    public void updateUser(UserUpdateReq updateInfo, String userid) throws IOException {
        Optional<User> user = userRepository.findByUserId(userid);
        User userinfo = user.get();

        System.out.println("파일");
        System.out.println(updateInfo.getThumbnail());

        if(updateInfo.getThumbnail() != null) //이미지 변경이 있으면
        {
            MultipartFile multipartFile = updateInfo.getThumbnail();

            String absolutePath = new File("").getAbsolutePath();
            String path = absolutePath + "/src/main/resources/profile/" + userinfo.getUserId();
            File file = new File(path);

            if(!file.exists()) {
                boolean wasSuccessful = file.mkdirs();
                if(!wasSuccessful)
                    System.out.println("file: was not successful");
            }

            // 파일의 확장자 추출
            String originalFileExtension;
            String contentType = multipartFile.getContentType();
            // 확장자명이 존재하지 않을 경우 처리 x
            if(ObjectUtils.isEmpty(contentType)) {

            }
            else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
                if(contentType.contains("image/jpeg"))
                    originalFileExtension = ".jpg";
                else if(contentType.contains("image/png"))
                    originalFileExtension = ".png";
                else  // 다른 확장자일 경우 처리 x
                {

                }
            }

            String final_name = path + File.separator + updateInfo.getThumbnail().getOriginalFilename();
            userinfo.setThumbnail(final_name);
            file = new File(final_name);
            multipartFile.transferTo(file);

            // 파일 권한 설정(쓰기, 읽기)
            file.setWritable(true);
            file.setReadable(true);

        }

        userinfo.setPassword(passwordEncoder.encode(updateInfo.getPassword()));
        userinfo.setNickname(updateInfo.getNickname());

        userRepository.save(userinfo);

    }
}
