package com.example.calendar.repository;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    // DB Query
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto);

    public List<ScheduleResponseDto> findAllSchedules(String userName, String updatedAt);
}
