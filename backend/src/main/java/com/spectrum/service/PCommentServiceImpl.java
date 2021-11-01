package com.spectrum.service;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.common.request.PCommentPostReq;
import com.spectrum.entity.PBoard;
import com.spectrum.entity.PComment;
import com.spectrum.entity.User;
import com.spectrum.repository.PBoardRepository;
import com.spectrum.repository.PCommentRepository;
import com.spectrum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        comment.setPBoard(pBoardOptional.get());

        String userId = jwtUtil.getUsername(token);
        System.out.println(userId);
        Optional<User> userOptional = userRepository.findByUserId(userId);
        comment.setUser(userOptional.get());

        pCommentRepository.save(comment);
    }


}
