package com.spectrum.service;

import com.spectrum.common.request.PBoardPostReq;
import com.spectrum.common.request.PBoardUpdateReq;
import com.spectrum.entity.PBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PBoardService {

    public List<PBoard> sortOfDistance() throws Exception;
    public void postPetSitter(PBoardPostReq pBoardPostReq, MultipartFile postPicture, String token) throws Exception;
    public boolean checkWriterOfBoard(PBoardUpdateReq pBoardUpdateReq, String token);
    public void updatePetSitter(PBoardUpdateReq pBoardUpdateReq, MultipartFile newPicture, String token) throws Exception;
    public void deletePetSitter(Long pboardId) throws IOException;
    public List<PBoard> myPetsitterList(String userid, Pageable pageable);
    public Object allPetsitterList(float longitude, float latitude, int pagenum);
    public PBoard detailOfPetsitter(Long pboardId);
    public int completedPetsitter(Long pboardId);
}
