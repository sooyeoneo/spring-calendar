package com.example.calendar.controllor;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.entity.Schedule;
import com.example.calendar.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// 클라이언트의 요청을 받고, 요청에 대한 처리를 Service Layer에 전달. Service에서 처리완료된 결과를 클라이언트에 응답
@RestController
@RequestMapping("/schedules") // prefix 하는 URL을 설정할 때 사용
public class ScheduleController {
    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping // 생성. PostMapping annotation 사용
    // 생성 시 데이터를 전달해도 되고 안해도 되는데, ScheduleResponseDto를 전달
    // ResponseEntity가 ScheduleResponseDto를 감싸준다. 값의 타입을 위해 ReponseDto로 바꾼다. Sevelet
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {
        scheduleService.createSchedule(dto); // ScheduleService로 dto 매개변수 값을 넘겨준다.
    }
}
