package com.spectrum.common.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChatSaveReq {
    List<ChatReq> chatList;
    String roomname;
}
