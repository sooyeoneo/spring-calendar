package com.example.calendar.repository;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    // DB Query
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedules(String userName, String updatedAt);

    Optional<ScheduleResponseDto> findScheduleById(Long id);

    String findPasswordById(Long id);

    int updateSchedule(Long id, ScheduleRequestDto dto);

    int deleteSchedule(Long id);
}
