package com.example.calendar.entity;

import com.example.calendar.dto.ScheduleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;  // 식별자 타입 Long, int 보다 큰 값, null을 포함하는 래퍼클래스
    private String userName;
    private String password;
    private String title;
    private String contents;
    private String createdAt;
    private String updatedAt;

    public Schedule(ScheduleRequestDto dto) {
        this.userName = dto.getUserName();
        this.password = dto.getPassword();
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }

    public Schedule(Long id, String userName, String password, String title, String contents) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.title = title;
        this.contents = contents;
    }
}
