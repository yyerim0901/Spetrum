package com.spectrum.common.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatReq {
    private String userName;
    private String content;

    public ChatReq() {
    }

    @Override
    public String toString() {
        return "{\"" +
                "userName\":\"" + userName + '\"' +
                ",\"content\":\"" + content + '\"' +
                "}┐";
    }

    public ChatReq(String userName, String content) {
        this.userName = userName;
        this.content = content;
    }
}
