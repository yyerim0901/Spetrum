package com.spectrum.service;

import com.spectrum.common.request.SBoardRegisterReq;
import com.spectrum.common.response.SBoardRes;
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
    public List<SBoardRes> getSBoardsByUser(User user) {
        List<SBoardRes> res = new ArrayList<>();
        List<SBoard> sboards = sBoardRepository.findByUser(user);
        for (SBoard sBoard : sboards) {
            List<SBoardFile> sboardf = sBoardFileRepository.findAllById(sBoard.getId());
            SBoardRes br = new SBoardRes();
            br.setFilelist(sboardf);
            br.setId(sBoard.getId());
            br.setContent(sBoard.getContent());
            br.setCreated(sBoard.getCreated());
            br.setUpdated(sBoard.getUpdated());
            br.setLikes(sBoard.getLikes());
            br.setFilelist(sboardf);
            res.add(br);
        }
        return res;
    }



    @Override
    public SBoard createSBoard(User user, SBoardRegisterReq sboardinfo, List<MultipartFile> sboardfiles) throws IOException {
        SBoard sBoard = new SBoard();
        sBoard.setUser(user);
        sBoard.setContent(sboardinfo.getContent());
        sBoard.setCreated(new Date());
        sBoard.setUpdated(new Date());
        sBoard.setLikes(0);
        SBoard tmpsboard =  sBoardRepository.save(sBoard);
        // 사진 저장하기
        List<SBoardFile> photoList = fileHandler.parseFileInfo(sboardfiles, user, tmpsboard);
        System.out.println(photoList);
        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()){
            for(SBoardFile photo : photoList)
                // 파일을 DB에 저장
                sBoardFileRepository.save(photo);
        }
        return sBoard;
    }

//    @Override
//    public SBoard getSBoardsById(Long sboardid) {
//        return sBoardRepository.getById(sboardid);
//    }

//    @Override
//    public SBoard putSBoard(User user, SBoardRegisterReq sboardinfo, List<MultipartFile> sboardfiles, Long sboardid) {
//        SBoard sBoard = sBoardRepository.getById(sboardid);
//        sBoard.setUser(user);
//        sBoard.setContent(sboardinfo.getContent());
//        sBoard.setUpdated(new Date());
//        SBoard tmpsboard =  sBoardRepository.save(sBoard);
//        // 사진은 수정 못하게 하자.
//        return sBoard;
//    }
//
//    @Override
//    public Boolean deleteSBoard(User user, Long sboardid) throws IOException {
//        SBoard sBoard = sBoardRepository.getById(sboardid);
//        if (sBoard == null){
//            return false;
//        }
//        sBoardRepository.delete(sBoard);
//        List<SBoardFile> photoList = sBoardFileRepository.findAllBySBoard(sBoard);
//        if(!photoList.isEmpty()){
//            for(SBoardFile photo : photoList)
//                // 파일을 DB에 저장
//                sBoardFileRepository.delete(photo);
//        }
//        return true;
//    }

}
