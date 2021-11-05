package com.spectrum.common.response;

import com.spectrum.entity.SBoard;
import com.spectrum.entity.SBoardFile;
import com.spectrum.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("UesrInfoResponse")
public class UesrInfoResponse {

    User user;
    List<User> followList;

    public static UesrInfoResponse of(User user, List<User> followList) {
        UesrInfoResponse res = new UesrInfoResponse();
        res.setUser(user);
        res.setFollowList(followList);
        return res;
    }
}
