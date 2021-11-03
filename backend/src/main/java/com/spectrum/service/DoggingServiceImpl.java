package com.spectrum.service;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.entity.Dogging;
import com.spectrum.entity.Path;
import com.spectrum.entity.User;
import com.spectrum.repository.DoggingRepository;
import com.spectrum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DoggingServiceImpl implements DoggingService{

    @Autowired
    private DoggingRepository doggingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public List<Dogging> MyDoggingList(String token){

        String userid = jwtUtil.getUsername(token);
        Optional<User> userOptional = userRepository.findByUserId(userid);
        User user = userOptional.get();
        return doggingRepository.findByUserId(user.getId());
    }

    @Override
    public void PostDogging(Dogging dogging, String token){

        String userid = jwtUtil.getUsername(token);
        Optional<User> userOptional = userRepository.findByUserId(userid);
        User user = userOptional.get();

        Date date = new Date();

        dogging.setDate(date);
        dogging.setUser(user);

//        List<Path> pathList = SaveDoggingPath(lats,lngs);
//        dogging.setPath(pathList);

        doggingRepository.save(dogging);
    }

//    @Override
//    public List<Path> SaveDoggingPath(Float[] lats, Float[] lngs){
//
//        int size = lats.length;
//        List<Path> list = new ArrayList<>();
//        for(int i=0; i<size; i++){
//            list.add(new Path(lats[i], lngs[i]));
//        }
//
//        return list;
//    }

    @Override
    public void DeleteDogging(Long id){
        doggingRepository.deleteById(id);
    }

}
