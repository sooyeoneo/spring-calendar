package com.example.calendar.service;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto);

    public List<ScheduleResponseDto> findAllSchedules(String userName, String updatedAt);
}
