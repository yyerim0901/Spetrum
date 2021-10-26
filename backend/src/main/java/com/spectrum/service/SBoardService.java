package com.spectrum.service;

import com.spectrum.common.response.SBoardRes;
import com.spectrum.entity.Quser;

import java.util.List;

public interface SBoardService {
    List<SBoardRes> getSBoardsByUser(Quser user);
}
