package com.example.calendar.service;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;

public interface ScheduleService {
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto);
}
