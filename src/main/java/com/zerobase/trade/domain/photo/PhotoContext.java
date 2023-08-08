package com.zerobase.trade.domain.photo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PhotoContext {
    CHAT_ROOM(true),
    PRODUCT(false);

    private final boolean isChatRoom;

    public boolean isChatRoom() {
        return isChatRoom;
    }
}