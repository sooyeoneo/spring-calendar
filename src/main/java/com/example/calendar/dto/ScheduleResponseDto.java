package com.example.calendar.dto;

import com.example.calendar.entity.Schedule; // Entity 클래스 Schedule을 가져와 ScheduleResponseDto 생성자에서 사용
import lombok.AllArgsConstructor; // 모든 필드를 포함하는 생성자를 자동으로 생성하는 Lombok 어노테이션
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto { // 응답용 DTO 클래스 - Schedule 데이터를 클라이언트에 전달하기 위한 객체
    private Long id; // 일정의 고유 식별자
    private String userName; // 일정 작성자 이름
    private String title; // 일정 제목
    private String contents; // 일정 내용
    private String createdAt; // 일정 생성 시간 (String 형태)
    private String updatedAt; // 일정 수정 시간 (String 형태)

    // Schedule 객체를 인자로 받는 생성자 - Entity에서 DTO로 변환 시 사용
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId(); // Schedule 객체의 id를 가져와 설정
        this.userName = schedule.getUserName(); // Schedule 객체의 userName을 가져와 설정
        this.title = schedule.getTitle(); // Schedule 객체의 title을 가져와 설정
        this.contents = schedule.getContents(); // Schedule 객체의 contents를 가져와 설정
        this.createdAt = schedule.getCreatedAt(); // Schedule 객체의 createdAt을 가져와 설정
        this.updatedAt = schedule.getUpdatedAt(); // Schedule 객체의 updatedAt을 가져와 설정
    }
}