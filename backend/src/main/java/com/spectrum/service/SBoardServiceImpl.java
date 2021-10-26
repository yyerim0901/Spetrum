package com.spectrum.service;

import com.spectrum.common.response.SBoardRes;
import com.spectrum.entity.Quser;
import com.spectrum.entity.SBoard;
import com.spectrum.repository.SBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SBoardServiceImpl implements SBoardService {
    @Autowired
    SBoardRepository sBoardRepository;

    @Override
    public List<SBoardRes> getSBoardsByUser(Quser user) {
        List<SBoardRes> res = new ArrayList<>();
        List<SBoard> sboards = sBoardRepository.findByUser(user);
        return null;
    }
}
