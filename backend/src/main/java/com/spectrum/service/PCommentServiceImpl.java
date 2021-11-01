package com.spectrum.service;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.common.request.PCommentPostReq;
import com.spectrum.common.request.PCommentUpdateReq;
import com.spectrum.entity.PBoard;
import com.spectrum.entity.PComment;
import com.spectrum.entity.User;
import com.spectrum.repository.PBoardRepository;
import com.spectrum.repository.PCommentRepository;
import com.spectrum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PCommentServiceImpl implements PCommentService{

    @Autowired
    private PCommentRepository pCommentRepository;

    @Autowired
    private PBoardRepository pBoardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public void writePComment(PCommentPostReq pCommentPostReq, String token){
        PComment comment = new PComment();
        comment.setContent(pCommentPostReq.getContent());

        Date date = new Date();
        comment.setCreated(date);

        Optional<PBoard> pBoardOptional = pBoardRepository.findById(pCommentPostReq.getPboardId());
        comment.setPboard(pBoardOptional.get());

        String userId = jwtUtil.getUsername(token);
        System.out.println(userId);
        Optional<User> userOptional = userRepository.findByUserId(userId);
        comment.setUser(userOptional.get());

        pCommentRepository.save(comment);
    }

    @Override
    public List<PComment> listPComment(Long pboardId){

        List<PComment> list = pCommentRepository.findByPboardId(pboardId);
        return list;
    }

    @Override
    public boolean checkPCommentWriter(Long pcommentId, String token){
        Optional<PComment> pCommentOptional = pCommentRepository.findById(pcommentId);
        PComment pComment = pCommentOptional.get();

        String userId = jwtUtil.getUsername(token);
        Optional<User> userOptional = userRepository.findByUserId(userId);
        User user = userOptional.get();

        if(pComment.getUser().equals(user)) return true;
        else return false;
    }

    @Override
    public void updatePComment(PCommentUpdateReq pCommentUpdateReq){
        Long commentId = pCommentUpdateReq.getPCommentId();
        PComment pComment = pCommentRepository.getById(commentId);

        Date date = new Date();
        pComment.setCreated(date);
        pComment.setContent(pCommentUpdateReq.getContent());

        pCommentRepository.save(pComment);
    }

    @Override
    public void deletePComment(Long pcommentId){
        PComment pComment = pCommentRepository.getById(pcommentId);
        pCommentRepository.delete(pComment);
    }

}
