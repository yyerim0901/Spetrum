package com.spectrum.common.response;

import com.spectrum.entity.SBoard;
import com.spectrum.entity.SBoardFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ApiModel("SBoardRes")
public class SBoardRes {

    @ApiModelProperty(name="id", example = "1")
    long id;

    @ApiModelProperty(name="content", example = "한줄sns내용")
    String content;

    @ApiModelProperty(name="like", example = "좋아요 수")
    int likes;

    @ApiModelProperty(name="created", example = "작성 날짜")
    Date created;

    @ApiModelProperty(name="updated", example = "수정 날짜")
    Date updated;

    @ApiModelProperty(name="list", example = "")
    List<SBoardFile> filelist;

    public static SBoardRes of(SBoard sBoard, List<SBoardFile> files) {
        SBoardRes res = new SBoardRes();
        res.setId(sBoard.getId());
        res.setCreated(sBoard.getCreated());
        res.setUpdated(sBoard.getUpdated());
        res.setContent(sBoard.getContent());
        res.setLikes(sBoard.getLikes());
        res.setFilelist(files);

        return res;
    }
}
