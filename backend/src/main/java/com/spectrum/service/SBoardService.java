package com.spectrum.service;

import com.spectrum.common.request.SBoardPutReq;
import com.spectrum.common.request.SBoardRegisterReq;
import com.spectrum.common.response.SBoardRes;
import com.spectrum.entity.SBoard;
import com.spectrum.entity.SBoardFile;
import com.spectrum.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SBoardService {
    List<SBoardRes> getSBoardsByUser(User user, Pageable pageable);

    SBoard createSBoard(User user, SBoardRegisterReq sboardinfo, List<MultipartFile> sboardfiles) throws IOException;

    SBoard getSBoardsById(Long sboardid);

    SBoard putSBoard(User user, SBoardPutReq sboardinfo, Long sboardid);

    Boolean deleteSBoard(User user, Long sboardid);

    List<SBoardFile> getFilesBysBoard(SBoard sboard);
}
