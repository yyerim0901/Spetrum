package com.spectrum.service;

import com.spectrum.common.request.PetSitterPostReq;
import org.springframework.web.multipart.MultipartFile;

public interface PetSitterService {

    public void postPetSitter(PetSitterPostReq petSitterPostRequest, MultipartFile postPicture);
    public void checkStatus(Long petSitterId);
}
