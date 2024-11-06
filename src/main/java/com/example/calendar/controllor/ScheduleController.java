package com.example.calendar.controllor;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.entity.Schedule;
import com.example.calendar.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// 클라이언트의 요청을 받고, 요청에 대한 처리를 Service Layer에 전달. Service에서 처리완료된 결과를 클라이언트에 응답
@RestController // @Controller + @ResponseBody
@RequestMapping("/schedules") // prefix 하는 URL을 설정할 때 사용
public class ScheduleController {

    // 주입된 의존성을 변경할 수 없어 객체의 상태를 안전하게 유지
    private final ScheduleService scheduleService;

    /**
     * 생성자 주입
     * 클래스가 필요로 하는 의존성을 생성자를 통해 전달하는 방식
     * @param scheduleService @Service로 등록된 ScheduleService 구현체인 Impl
     */
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 일정 생성 API
     * @param {@link ScheduleRequestdto} 스케줄 생성 요청 객체
     * @return {@link ResponseEntity<ScheduleResponseDto>} JSON 응답
     */
    @PostMapping // 생성 요청. PostMapping annotation 사용
    // 생성 시 데이터를 전달해도 되고 안해도 되는데, ScheduleResponseDto를 전달
    // ResponseEntity가 ScheduleResponseDto를 감싸준다. 값의 타입을 위해 ReponseDto로 바꾼다. Sevelet
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {
        scheduleService.createSchedule(dto); // ScheduleService로 dto 매개변수 값을 넘겨준다.
        // ServiceLayer 호출 및 응답
        return new ResponseEntity<>(scheduleService.createSchedule(dto), HttpStatus.CREATED);
    }
}
