package com.example.calendar.service;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;

import java.util.List;
import java.util.Optional;

// 일정 관련 서비스 로직을 정의하는 인터페이스로, 구현체가 실질적인 비즈니스 로직을 수행함
public interface ScheduleService {

    // 일정 생성, 요청된 일정 데이터를 사용해 새로운 일정을 생성하고 결과를 dto로 반환
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);

    // 전체 일정 조회, 작성자명과 수정일을 기준으로 일정을 조회해 리스트 형태로 반환
    List<ScheduleResponseDto> findAllSchedules(String userName, String updatedAt);

    // 단일 일정 조회, 일정 Id로 단일 일정 조회하여 결과를 dto로 반환
    ScheduleResponseDto findScheduleById(Long id);

    // 일정 수정, 요청된 Id와 데이터로 일정을 수정하고 결과를 dto로 반환
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto);

    // 일정 삭제, Id와 비밀번호를 기반으로 일정을 삭제하고, 결과를 반환하지 않음
    void deleteSchedule(Long id, String password);
}

