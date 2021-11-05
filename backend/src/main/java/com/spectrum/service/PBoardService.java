package com.spectrum.service;

import com.spectrum.common.request.PBoardPostReq;
import com.spectrum.common.request.PBoardUpdateReq;
import com.spectrum.entity.PBoard;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PBoardService {

    public void postPetSitter(PBoardPostReq petSitterPostRequest, MultipartFile postPicture, String token) throws IOException;
    public boolean checkWriterOfBoard(PBoardUpdateReq pBoardUpdateReq, String token);
    public void updatePetSitter(PBoardUpdateReq petSitterUpdateReq, MultipartFile newPicture, String token) throws IOException;
    public void deletePetSitter(Long petSitterId) throws IOException;
    public List<PBoard> myPetsitterList(String token);
    public List<PBoard> allPetsitterList();
    public PBoard detailOfPetsitter(Long petsitterId);
}
