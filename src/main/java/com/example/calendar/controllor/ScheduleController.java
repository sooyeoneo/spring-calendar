package com.example.calendar.controllor;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.entity.Schedule;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedules") // prefix 하는 URL을 설정할 때 사용
public class ScheduleController {
    private final Map<Long, Schedule> scheduleList = new HashMap<>(); // HashMap으로 scheduleList 자료구조 초기화

    @PostMapping // 생성. PostMapping annotation 사용
    // 생성 시 데이터를 전달해도 되고 안해도 되는데, ScheduleResponseDto를 전달
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto dto) {
        // 식별자가 1씩 증가 하도록 만듦, 비어있다면 1
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1; // Collections.max()라는 메서드는 scheduleList.keySet()에서 최댓값을 뽑아낸다. scheduleList.keySet()은 key(Long)값을 다 꺼내본다.
        // 요청받은 데이터로 Schedule 객체 생성, RequestDto 형태로 요청 받은 것을 Schdule 객체로 바꿔주기
        Schedule schedule = new Schedule(scheduleId, dto.getTitle(), dto.getContents());
        // 프로젝트가 실행되었다가 종료될 때, scheduleList 안의 데이터가 지워짐 -> Inmemory DB에 Schedule 스케줄
        scheduleList.put(scheduleId, schedule);

        return new ScheduleResponseDto(schedule);
    }
}
