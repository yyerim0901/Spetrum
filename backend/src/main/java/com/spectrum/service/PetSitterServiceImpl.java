package com.spectrum.service;

import com.spectrum.common.request.PetSitterPostReq;
import com.spectrum.common.request.PetSitterUpdateReq;
import com.spectrum.entity.PetSitter;
import com.spectrum.repository.PetSitterRepository;
import com.spectrum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class PetSitterServiceImpl implements PetSitterService{

    @Autowired
    private PetSitterRepository petSitterRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void postPetSitter(PetSitterPostReq petSitterPostRequest, MultipartFile postPicture){
        PetSitter petSitter = new PetSitter();
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
    public void deletePetSitter(Long petSitterId){
        petSitterRepository.deleteById(petSitterId);
    }


}
