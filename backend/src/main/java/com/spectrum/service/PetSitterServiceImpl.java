package com.spectrum.service;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.common.request.PetSitterPostReq;
import com.spectrum.common.request.PetSitterUpdateReq;
import com.spectrum.entity.PetSitter;
import com.spectrum.entity.User;
import com.spectrum.repository.PetSitterRepository;
import com.spectrum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PetSitterServiceImpl implements PetSitterService{

    @Autowired
    private PetSitterRepository petSitterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public void postPetSitter(PetSitterPostReq petSitterPostRequest, MultipartFile postPicture, String token){
        PetSitter petSitter = new PetSitter();

//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRlc3QiLCJpYXQiOjE2MzU3MzE1NTgsImV4cCI6MTYzNTczMTU2OH0.LWmSTaNvpoqtDKV2SJ5vkOBu21iLnWHGdV6MUYV0jxw";
        String userId = jwtUtil.getUsername(token);
        System.out.println(userId);
        Optional<User> userOptional = userRepository.findByUserId(userId);
        petSitter.setUser(userOptional.get());

        Date date = new Date();

        petSitter.setTitle(petSitterPostRequest.getTitle());
        petSitter.setContent(petSitterPostRequest.getContent());
        //위경도 프론트에서 가지고 오기
        petSitter.setLng(petSitterPostRequest.getLng());
        petSitter.setLat(petSitterPostRequest.getLat());

        petSitter.setCreated(date);
        petSitter.setStatus(0); //글 작성시에는 0으로 default

        String url = "이미지경로/";
        if(postPicture != null) {
            String imgPath = postPicture.getOriginalFilename();
            petSitter.setPicture(url + imgPath);
        }else{
            //이미지 없는 경우
            petSitter.setPicture(url);
        }
//        user user = new user();
//        userRepository.findById(petSitterPostRequest.getUser_pk());
//        petSitter.setUser();

        petSitterRepository.save(petSitter);
    }

    //글 작성 후 거래 시 인증 과정
    @Override
    public void checkStatus(Long petSitterId){
        //status0인지 체크, 글id 필요

    }

    @Override
    public void updatePetSitter(PetSitterUpdateReq petSitterUpdateReq, MultipartFile newPicture){

        Long id = petSitterUpdateReq.getId();
        PetSitter petSitter = petSitterRepository.getById(id);

        Date date = new Date();
        petSitter.setUpdated(date);

        petSitter.setTitle(petSitterUpdateReq.getTitle());
        petSitter.setContent(petSitterUpdateReq.getContent());
        petSitter.setLat(petSitterUpdateReq.getLat());
        petSitter.setLng(petSitterUpdateReq.getLng());

        String url = "이미지경로/";
        if(newPicture != null) {
            String imgPath = newPicture.getOriginalFilename();
            petSitter.setPicture(url + imgPath);
        }else{
            //새로운 수정이미지가 없는 경우 그냥 이전 이미지 들고와서 사용
            petSitter.setPicture(petSitterUpdateReq.getPicture());
        }
        petSitterRepository.save(petSitter);

    }
    @Override
    public void deletePetSitter(Long petSitterId){
        petSitterRepository.deleteById(petSitterId);
    }

    @Override
    public List<PetSitter> myPetsitterList(String token){
//        String userId = jwtUtil.getUsername(token);
        String userId = "test";
        Optional<User> userOptional = userRepository.findByUserId(userId);
        User user = userOptional.get();
        System.out.println(user.getId());
        return petSitterRepository.findAllByUserId(user.getId());
    }

    @Override
    public List<PetSitter> allPetsitterList(){
        List<PetSitter> allList = petSitterRepository.findAll();
        return allList;
    }

    @Override
    public PetSitter detailOfPetsitter(Long petsitterId){
        Optional<PetSitter> petOp = petSitterRepository.findById(petsitterId);
        return petOp.get();
    }

}
