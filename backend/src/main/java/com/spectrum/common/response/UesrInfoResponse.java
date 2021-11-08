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
    List<String> followList;
    List<String> followerList;

    public static UesrInfoResponse of(User user, List<String> followList,List<String> followerList) {
        UesrInfoResponse res = new UesrInfoResponse();
        res.setUser(user);
        res.setFollowList(followList);
        res.setFollowerList(followerList);
        return res;
    }
}
