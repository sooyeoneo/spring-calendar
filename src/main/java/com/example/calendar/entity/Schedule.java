package com.example.calendar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;  // 식별자 타입 Long, int 보다 큰 값, null을 포함하는 래퍼클래스
    private String title;
    private String body;
    private String user_name;
    private String password;
    private String created_at;
    private String updated_at;
}
