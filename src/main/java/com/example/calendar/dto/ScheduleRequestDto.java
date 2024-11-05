package com.example.calendar.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String contents;
    private String userName;
    private String password;
}
