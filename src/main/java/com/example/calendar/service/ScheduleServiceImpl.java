package com.example.calendar.service;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.entity.Schedule;
import com.example.calendar.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Annotation @Service는 @Component와 같다, Spring Bean으로 등록한다는 뜻.
 * Spring Bean으로 등록되면 다른 클래스에서 주입하여 사용할 수 있다.
 * 명시적으로 Service Layer 라는것을 나타낸다.
 * 비지니스 로직을 수행한다.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService { // ScheduleService 인터페이스를 구현한 구현체
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {

        // repository.saveSchedule(schedule);
        // DB에 저장
        ScheduleResponseDto result = scheduleRepository.createSchedule(dto);
        return result;
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(String userName, String updatedAt) {
        List<ScheduleResponseDto> result = scheduleRepository.findAllSchedules(userName, updatedAt);
        return result;
    }
}
