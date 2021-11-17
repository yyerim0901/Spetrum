package com.spectrum.service;

import com.spectrum.common.request.FollowReq;
import com.spectrum.common.request.UserRegisterPostReq;
import com.spectrum.common.request.UserUpdateReq;
import com.spectrum.entity.Follow;
import com.spectrum.entity.User;
import com.spectrum.repository.FollowRepository;
import com.spectrum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowRepository followRepository;

    String BASE_PATH = "/var/lib/jenkins/workspace/PJT/backend/src/main/resources/";

    @Override
    public void createUser(UserRegisterPostReq registerInfo) throws IOException {
        User res = new User();
        MultipartFile thumbnail = registerInfo.getThumbnail();

        if(thumbnail == null)
        {
            res.setThumbnail("image/profile/default.png");
        }
        else
        {
            String path = BASE_PATH+"image/profile/"+ registerInfo.getUserId();
            File file = new File(path);
            if(!file.exists()) {
                file.mkdirs();
            }
            String final_name = path + File.separator + thumbnail.getOriginalFilename();
            res.setThumbnail("image/profile/" + registerInfo.getUserId() + File.separator + thumbnail.getOriginalFilename());

            file = new File(final_name);
            thumbnail.transferTo(file);

            System.out.println(final_name);

            // 파일 권한 설정(쓰기, 읽기)
            file.setWritable(true);
            file.setReadable(true);
        }


        res.setUserId(registerInfo.getUserId());
        res.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
        res.setNickname(registerInfo.getNickname());


        User tmpuser = userRepository.save(res);
        // sns 유저 폴더 생성
        String user_path = "/var/lib/jenkins/workspace/PJT/backend/src/main/resources/image/sns/" + tmpuser.getId();
//        String user_path = new File("").getAbsolutePath() + "/src/main/resources/image/sns/" + tmpuser.getId();
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

        if(updateInfo.getThumbnail() != null) //이미지 변경이 있으면
        {
            MultipartFile multipartFile = updateInfo.getThumbnail();

            String path = BASE_PATH  + userinfo.getUserId();

            File file = new File(path);

            if(!file.exists()) {
                file.mkdirs();
            }

            String final_name = path + File.separator + updateInfo.getThumbnail().getOriginalFilename();
            userinfo.setThumbnail(final_name);
            file = new File(final_name);
            multipartFile.transferTo(file);

            // 파일 권한 설정(쓰기, 읽기)
            file.setWritable(true);
            file.setReadable(true);
        }
        if(updateInfo.getPassword() != null)
        {
            userinfo.setPassword(passwordEncoder.encode(updateInfo.getPassword()));
        }
        if(updateInfo.getNickname() != null)
            userinfo.setNickname(updateInfo.getNickname());
        if(updateInfo.getIntroduce() != null)
            userinfo.setIntroduce(updateInfo.getIntroduce());

        userinfo.setTemperature(updateInfo.getTemperature());
        userinfo.setPetpit(updateInfo.getPETPTI());
        userinfo.setKeyword(updateInfo.getKeyword());

        userRepository.save(userinfo);
    }

    @Override
    public void deleteUser(String userid) {
        Optional<User> user = userRepository.findByUserId(userid);
        userRepository.delete(user.get());

        String path = BASE_PATH  + userid;
        File file = new File(path);

        try {
            while(file.exists()) {
                File[] folder_list = file.listFiles(); //파일리스트 얻어오기

                for (int j = 0; j < folder_list.length; j++) {
                    folder_list[j].delete(); //파일 삭제
                }

                if(folder_list.length == 0 && file.isDirectory()){
                    file.delete(); //대상폴더 삭제
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void follow(FollowReq followReq) {
        String from = followReq.getFrom();
        String to = followReq.getTo();
        Follow follow = new Follow();

        User fromuser = userRepository.findByUserId(from).get();
        User touser = userRepository.findByUserId(to).get();

        Optional<Follow> fw = followRepository.findByFollowAndFollower(touser,fromuser);
        if(fw.isPresent())
        {
            unfollow(fw.get());
        }
        else
        {
            follow.setFollower(fromuser);
            follow.setFollow(touser);

            followRepository.save(follow);
        }
    }

    @Override
    public List<User> searchUserId(String userid) {
        List<User> userlist = userRepository.findAll();

        List<User> res = new ArrayList();
        for (User user : userlist)
        {
            User u = new User();
            if(user.getUserId().contains(userid))
            {
                u.setUserId(user.getUserId());
                u.setNickname(user.getNickname());
                u.setThumbnail(user.getThumbnail());

                res.add(u);
            }
        }

        return res;
    }

    @Override
    public String followCheck(String userid, String myid) {

        User my = userRepository.findByUserId(myid).get();
        User user = userRepository.findByUserId(userid).get();
//        System.out.println("myid "+ my.getUserId());
//        System.out.println("userid "+ user.getUserId());

        Optional<Follow> follow = followRepository.findByFollowAndFollower(user,my);
        if (follow.isPresent())
        {
            return "A";
        }
        else
            return "B";
    }
    @Override
    public List<String> followList(String userid) {
        User user = userRepository.findByUserId(userid).get();
        List<Follow> followlist = followRepository.findAllByFollower(user);
        List<String> userList = new ArrayList();

        for (Follow follow : followlist)
        {
            userList.add(follow.getFollow().getUserId());
        }
        return userList;
    }
    @Override
    public List<String> followerList(String userid) {
        User user = userRepository.findByUserId(userid).get();
        List<Follow> followlist = followRepository.findAllByFollow(user);
        List<String> userList = new ArrayList();

        for (Follow follow : followlist)
        {
            userList.add(follow.getFollower().getUserId());
        }
        return userList;
    }

    @Override
    public void unfollow(Follow follow) {

        followRepository.delete(follow);
    }
}
