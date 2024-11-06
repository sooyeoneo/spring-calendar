package com.example.calendar.repository;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;

public interface ScheduleRepository {

    // DB Query
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto);
}
