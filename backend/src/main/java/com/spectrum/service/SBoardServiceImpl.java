package com.spectrum.service;

import com.spectrum.common.request.SBoardRegisterReq;
import com.spectrum.common.response.SBoardRes;
import com.spectrum.entity.QUser;
import com.spectrum.entity.SBoard;
import com.spectrum.entity.SBoardFile;
import com.spectrum.entity.User;
import com.spectrum.repository.SBoardFileRepository;
import com.spectrum.repository.SBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SBoardServiceImpl implements SBoardService {
    @Autowired
    SBoardRepository sBoardRepository;
    @Autowired
    SBoardFileRepository sBoardFileRepository;
    @Autowired
    FileHandler fileHandler;

    @Override
    public List<SBoardRes> getSBoardsByUser(QUser user) {
        List<SBoardRes> res = new ArrayList<>();
        List<SBoard> sboards = sBoardRepository.findByUser(user);
        for (SBoard sBoard : sboards) {

        }
        return null;
    }

    @Override
    public void createSBoard(QUser user, SBoardRegisterReq sboardinfo, List<MultipartFile> sboardfiles) throws IOException {
        SBoard sBoard = new SBoard();
        sBoard.setUser(new User()); // 수정할 것
        sBoard.setContent(sboardinfo.getContent());
        sBoard.setCreated(new Date());
        sBoard.setUpdated(new Date());
        sBoard.setLikes(0);

        // 사진 저장하기
        List<SBoardFile> photoList = fileHandler.parseFileInfo(sboardfiles);

        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()){
            for(SBoardFile photo : photoList)
                // 파일을 DB에 저장
                sBoardFileRepository.save(photo);
        }
    }
}
