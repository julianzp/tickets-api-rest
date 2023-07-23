package com.doublev.tickets.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class TicketsHttpMessage {

    private String timestamp;
    private Integer status;
    private String message;
}
