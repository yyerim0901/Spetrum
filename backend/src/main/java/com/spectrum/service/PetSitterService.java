package com.spectrum.service;

import com.spectrum.common.request.PetSitterPostReq;
import com.spectrum.common.request.PetSitterUpdateReq;
import com.spectrum.entity.PetSitter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PetSitterService {

    public void postPetSitter(PetSitterPostReq petSitterPostRequest, MultipartFile postPicture, String token);
    public void checkStatus(Long petSitterId);
    public void updatePetSitter(PetSitterUpdateReq petSitterUpdateReq, MultipartFile newPicture);
    public void deletePetSitter(Long petSitterId);
    public List<PetSitter> myPetsitterList(String token);
}
