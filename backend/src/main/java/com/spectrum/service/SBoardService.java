package com.spectrum.service;

import com.spectrum.common.request.SBoardRegisterReq;
import com.spectrum.common.response.SBoardRes;
import com.spectrum.entity.QUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SBoardService {
    List<SBoardRes> getSBoardsByUser(QUser user);
    void createSBoard(QUser user, SBoardRegisterReq sboardinfo, List<MultipartFile> sboardfiles) throws IOException;
}
