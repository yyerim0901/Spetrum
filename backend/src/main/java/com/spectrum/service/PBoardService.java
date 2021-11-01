package com.spectrum.service;

import com.spectrum.common.request.PBoardPostReq;
import com.spectrum.common.request.PBoardUpdateReq;
import com.spectrum.entity.PBoard;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PBoardService {

    public void postPetSitter(PBoardPostReq petSitterPostRequest, MultipartFile postPicture, String token);
    public void checkStatus(Long petSitterId);
    public void updatePetSitter(PBoardUpdateReq petSitterUpdateReq, MultipartFile newPicture);
    public void deletePetSitter(Long petSitterId);
    public List<PBoard> myPetsitterList(String token);
    public List<PBoard> allPetsitterList();
    public PBoard detailOfPetsitter(Long petsitterId);
}
