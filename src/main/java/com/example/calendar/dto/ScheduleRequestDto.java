package com.example.calendar.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String userName;
    private String password;
    private String title;
    private String contents;
}
