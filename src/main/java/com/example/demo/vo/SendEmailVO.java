package com.example.demo.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendEmailVO {

    private String key;
    private String value;
}
