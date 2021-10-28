package com.spectrum.service;

import com.spectrum.common.response.SBoardRes;
import com.spectrum.entity.QUser;

import java.util.List;

public interface SBoardService {
    List<SBoardRes> getSBoardsByUser(QUser user);
}
