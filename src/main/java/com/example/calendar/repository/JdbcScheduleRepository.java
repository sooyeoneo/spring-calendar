package com.example.calendar.repository;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.entity.Schedule;

import javax.sql.DataSource;

public class JdbcScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        // 요청받은 데이터로 Schedule 객체 생성
        Schedule schedule = new Schedule(dto.getTitle(), dto.getContents());

        // 저장
        return scheduleReposiroy.creatSchedule(schedule);
    }
}
