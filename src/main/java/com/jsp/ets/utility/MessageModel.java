package com.jsp.ets.utility;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class MessageModel {
    private String to;
    private Date sentDate;
    private String subject;
    private String text;
}
