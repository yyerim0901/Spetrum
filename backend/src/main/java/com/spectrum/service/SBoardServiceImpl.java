package com.spectrum.service;

import com.spectrum.common.request.SBoardRegisterReq;
import com.spectrum.common.response.SBoardRes;
import com.spectrum.entity.SBoard;
import com.spectrum.entity.SBoardFile;
import com.spectrum.entity.User;
import com.spectrum.repository.SBoardFileRepository;
import com.spectrum.repository.SBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SBoardServiceImpl implements SBoardService {
    @Autowired
    SBoardRepository sBoardRepository;
    @Autowired
    SBoardFileRepository sBoardFileRepository;
    @Autowired
    FileHandler fileHandler;

    String BASE_PATH = "/var/lib/jenkins/workspace/PJT/backend/src/main/resources/image/";
//    String BASE_PATH = new File("").getAbsolutePath() + "/src/main/resources/image/";

    @Override
    public List<SBoardRes> getSBoardsByUser(User user, Pageable pageable) {
        List<SBoardRes> res = new ArrayList<>();
        List<SBoard> sboards = sBoardRepository.findAllByUser(user, pageable);
        for (SBoard sBoard : sboards) {
            Optional<List<SBoardFile>> sboardf = sBoardFileRepository.findBysBoard(sBoard);
            System.out.println(sboardf.get());
            SBoardRes br = new SBoardRes();
            br.setFilelist(sboardf.get());
            br.setId(sBoard.getId());
            br.setContent(sBoard.getContent());
            br.setCreated(sBoard.getCreated());
            br.setUpdated(sBoard.getUpdated());
            br.setLikes(sBoard.getLikes());
            br.setFilelist(sboardf.get());
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

        // 파일을 저장할 세부 경로 지정
        String path = BASE_PATH + "sns/" + user.getId() +'/'+ tmpsboard.getId();
        File file = new File(path);

        // 디렉터리가 존재하지 않을 경우
        if(!file.exists()) {
            boolean wasSuccessful = file.mkdirs();
            // 디렉터리 생성에 실패했을 경우
            if(!wasSuccessful)
                System.out.println("file: was not successful");
        }
        file.setWritable(true);
        file.setReadable(true);

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

    @Override
    public SBoard getSBoardsById(Long sboardid) {
        return sBoardRepository.getById(sboardid);
    }

    @Override
    public SBoard putSBoard(User user, SBoardRegisterReq sboardinfo, List<MultipartFile> sboardfiles, Long sboardid) {
        SBoard sBoard = sBoardRepository.getById(sboardid);
        sBoard.setUser(user);
        sBoard.setContent(sboardinfo.getContent());
        sBoard.setUpdated(new Date());
        SBoard tmpsboard =  sBoardRepository.save(sBoard);
        // 사진은 수정 못하게 하자.
        return sBoard;
    }

    @Override
    public Boolean deleteSBoard(User user, Long sboardid) {
        Optional<SBoard> sb = sBoardRepository.findById(sboardid);
        if (sb == null){
            return false;
        }
        sBoardRepository.delete(sb.get());
        Optional<List<SBoardFile>> photoList = sBoardFileRepository.findBysBoard(sb.get());
        fileHandler.deleteFile(user, sboardid, photoList);
        if(!photoList.get().isEmpty()){
            for(SBoardFile photo : photoList.get())
                // 파일을 DB에 저장
                sBoardFileRepository.delete(photo);
        }
        return true;
    }

    @Override
    public List<SBoardFile> getFilesBysBoard(SBoard sboard) {
        Optional<List<SBoardFile>> files = sBoardFileRepository.findBysBoard(sboard);
        return files.get();
    }

}
