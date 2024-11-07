package com.example.calendar.dto; // DTO(Data Transfer Object) 클래스의 패키지 경로

import lombok.Getter; // Lombok 라이브러리의 Getter 어노테이션을 사용하여 모든 필드의 Getter 메서드를 자동 생성

@Getter
public class ScheduleRequestDto { // 클라이언트 요청 시 데이터 전달을 위한 dto 클래스, 주로 일정 생성/수정 시 사용
    private String userName; // 일정 작성자 이름
    private String password; // 일정 비밀번호, 데이터 보호 목적
    private String title; // 일정 제목
    private String contents; // 일정 내용
}
