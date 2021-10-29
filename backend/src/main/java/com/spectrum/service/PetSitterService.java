package com.spectrum.service;

import com.spectrum.common.request.PetSitterPostReq;
import com.spectrum.common.request.PetSitterUpdateReq;
import org.springframework.web.multipart.MultipartFile;

public interface PetSitterService {

    public void postPetSitter(PetSitterPostReq petSitterPostRequest, MultipartFile postPicture);
    public void checkStatus(Long petSitterId);
    public void updatePetSitter(PetSitterUpdateReq petSitterUpdateReq, MultipartFile newPicture);
    public void deletePetSitter(Long petSitterId);
}
