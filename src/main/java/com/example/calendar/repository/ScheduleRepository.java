package com.example.calendar.repository;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;

import java.util.List;
import java.util.Optional;

// ScheduleRepository 일정과 관련된 데이터베이스 작업을 처리하는 메서드 정의
public interface ScheduleRepository {

    // 새 일정 생성, 생성된 일정에 대한 응답 정보를 반환
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);

    // 사용자와 업데이트 날짜로 일정 목록 조회
    List<ScheduleResponseDto> findAllSchedules(String userName, String updatedAt);

    // Id로 일정 조회, 일정이 존재하지 않으면 Optional.empty()를 반환
    Optional<ScheduleResponseDto> findScheduleById(Long id);

    // Id로 비밀번호 조회, 일정이 존재하지 않거나 비밀번호가 없으면 Optional.empty()를 반환
    Optional<String> findPasswordById(Long id);

    // Id로 일정 수정, 수정된 행의 수를 반환하며, 일반적으로 0 또는 1을 반환
    int updateSchedule(Long id, ScheduleRequestDto dto);

    // Id로 일정 삭제, 삭제된 행의 수를 반환하며, 일반적으로 0 또는 1을 반환
    int deleteSchedule(Long id);
}


