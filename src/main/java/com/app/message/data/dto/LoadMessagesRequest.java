package com.app.message.data.dto;

import lombok.Data;

@Data
public class LoadMessagesRequest {
    private Long channelId;
    private int pageSize;
    private int pageNumber;
}
