package com.spectrum.service;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.common.request.PBoardPostReq;
import com.spectrum.common.request.PBoardUpdateReq;
import com.spectrum.entity.PBoard;
import com.spectrum.entity.PComment;
import com.spectrum.entity.User;
import com.spectrum.repository.PBoardRepository;
import com.spectrum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PBoardServiceImpl implements PBoardService {

    @Autowired
    private PBoardRepository pBoardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public void postPetSitter(PBoardPostReq petSitterPostRequest, MultipartFile postPicture, String token){
        PBoard petSitter = new PBoard();

        String userId = jwtUtil.getUsername(token);
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

        pBoardRepository.save(petSitter);
    }

    //글 수정 시 권한 검사
    @Override
    public boolean checkWriterOfBoard(PBoardUpdateReq pBoardUpdateReq, String token){
        //status0인지 체크, 글id 필요
        Optional<PBoard> pBoardOptional = pBoardRepository.findById(pBoardUpdateReq.getId());
        PBoard pBoard = pBoardOptional.get();

        String userId = jwtUtil.getUsername(token);
        Optional<User> userOptional = userRepository.findByUserId(userId);
        User user = userOptional.get();

        if(pBoard.getUser().equals(user)) return true;
        else return false;
    }

    @Override
    public void updatePetSitter(PBoardUpdateReq petSitterUpdateReq, MultipartFile newPicture){

        Long id = petSitterUpdateReq.getId();
        PBoard petSitter = pBoardRepository.getById(id);

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
        pBoardRepository.save(petSitter);

    }
    @Override
    public void deletePetSitter(Long petSitterId){
        pBoardRepository.deleteById(petSitterId);
    }

    @Override
    public List<PBoard> myPetsitterList(String token){
        String userId = jwtUtil.getUsername(token);
        Optional<User> userOptional = userRepository.findByUserId(userId);
        User user = userOptional.get();
        System.out.println(user.getId());
        return pBoardRepository.findAllByUserId(user.getId());
    }

    @Override
    public List<PBoard> allPetsitterList(){
        List<PBoard> allList = pBoardRepository.findAll();
        return allList;
    }

    @Override
    public com.spectrum.entity.PBoard detailOfPetsitter(Long petsitterId){
        Optional<com.spectrum.entity.PBoard> petOp = pBoardRepository.findById(petsitterId);
        return petOp.get();
    }

}
