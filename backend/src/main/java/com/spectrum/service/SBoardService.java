package com.spectrum.service;

import com.spectrum.common.request.SBoardRegisterReq;
import com.spectrum.common.response.SBoardRes;
import com.spectrum.entity.SBoard;
import com.spectrum.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SBoardService {
    List<SBoardRes> getSBoardsByUser(User user);
    SBoard createSBoard(User user, SBoardRegisterReq sboardinfo, List<MultipartFile> sboardfiles) throws IOException;

//    SBoard getSBoardsById(Long sboardid);

//    SBoard putSBoard(User user, SBoardRegisterReq sboardinfo, List<MultipartFile> sboardfiles, Long sboardid);
//
//    Boolean deleteSBoard(User user, Long sboardid) throws IOException;
}
