package com.spectrum.service;

import com.spectrum.common.request.SCommentRegisterReq;
import com.spectrum.entity.SBoard;
import com.spectrum.entity.SComment;
import com.spectrum.entity.User;
import com.spectrum.repository.SBoardRepository;
import com.spectrum.repository.SCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SCommentServiceImpl implements SCommentService {

    @Autowired
    SCommentRepository sCommentRepository;

    @Autowired
    SBoardRepository sBoardRepository;

    @Override
    public List<SComment> getSCommentsBysBoard(SBoard sboard) {
        Optional<List<SComment>> scomments = sCommentRepository.findBysBoard(sboard);
        return scomments.get();
    }

    @Override
    public SComment createSComment(User user, SCommentRegisterReq scommentinfo, Long sboardid) {
        Optional<SBoard> sboard = sBoardRepository.findById(sboardid);
        if (sboard.isPresent()){
            SComment scomment = new SComment();
            scomment.setContent(scommentinfo.getContent());
            scomment.setCreated(new Date());
            scomment.setUser(user);
            scomment.setSBoard(sboard.get());
            sCommentRepository.save(scomment);
            return scomment;
        }
        else {
            return null;
        }
    }

    @Override
    public SComment getSCommentsById(Long scommentid) {
        Optional<SComment> scomment = sCommentRepository.findById(scommentid);
        if (scomment.isPresent()){
            return scomment.get();
        }
        else {
            return null;
        }
    }

    @Override
    public SComment putSComment(User user, SCommentRegisterReq scommentinfo, Long scommentid) {
        Optional<SComment> scomment = sCommentRepository.findById(scommentid);
        scomment.get().setContent(scommentinfo.getContent());
        scomment.get().setCreated(new Date());
        sCommentRepository.save(scomment.get());
        return scomment.get();
    }

    @Override
    public Boolean deleteSComment(User user, Long scommentid) {
        Optional<SComment> scomment = sCommentRepository.findById(scommentid);
        if (scomment.isPresent()){
            sCommentRepository.delete(scomment.get());
            return true;
        }
        else {
            return false;
        }
    }
}
