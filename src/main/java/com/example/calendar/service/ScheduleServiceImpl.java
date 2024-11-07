package com.example.calendar.service;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.entity.Schedule;
import com.example.calendar.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

/**
 * Annotation @Service는 @Component와 같다, Spring Bean으로 등록한다는 뜻.
 * Spring Bean으로 등록되면 다른 클래스에서 주입하여 사용할 수 있다.
 * 명시적으로 Service Layer 라는것을 나타낸다.
 * 비지니스 로직을 수행한다.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService { // ScheduleService 인터페이스를 구현한 구현체
    // 데이터 접근을 위한 ScheduleRepository 인스턴스
    private final ScheduleRepository scheduleRepository;

    // 생성자 주입을 통해 ScheduleRespository 주입
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        // Schedule 데이터를 DB에 저장하고 결과 반환
        ScheduleResponseDto result = scheduleRepository.createSchedule(dto);
        return result;
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(String userName, String updatedAt) {
        // 조건에 맞는 일정 목록을 조회
        List<ScheduleResponseDto> result = scheduleRepository.findAllSchedules(userName, updatedAt);
        return result;
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        // id 식별자의 scheduleResponseDto가 없다면?
        // List의 경우 데이터가 없으면 빈배열을 응답했지만 ScheduleResponseDto 객체 하나 조회의 경우
        Optional<ScheduleResponseDto> optionalSchedule = scheduleRepository.findScheduleById(id);

        // 조회된 일정이 없을 경우 예외 발생으로 NPE(NullPointerException) 방지
        if (optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 id입니다. = " + id);
        }
        return optionalSchedule.get();
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        // id로 일정 비밀번호 조회
        Optional<String> password = scheduleRepository.findPasswordById(id);

        if (password.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 id입니다. = " + id);
        }

        // 필수 값 누락 확인
        if (dto.getUserName() == null || dto.getPassword() == null || dto.getTitle() == null || dto.getContents() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "필수 정보를 모두 입력하세요.");
        }

        // 비밀번호 일치 확인
        if (!dto.getPassword().equals(password.get())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "올바른 비밀번호를 입력하세요."); // 401번...
        }

        // 업데이트 실행 결과 확인
        int updatedRow = scheduleRepository.updateSchedule(id, dto);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "수정된 데이터가 없습니다.");
        }

        // 조회된 데이터를 Optional에서 꺼내어 반환
        return scheduleRepository.findScheduleById(id).get(); //  .Get() Optional에 감싸진 데이터를 꼭 꺼내오자.
    }

    @Override
    public void deleteSchedule(Long id, String password) {
        // 주어진 id로 일정 비밀번호 조회
        Optional<String> dbPassword = scheduleRepository.findPasswordById(id);
        if (dbPassword.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 id입니다. = " + id);
        }

        // 비밀번호 일치 여부 확인
        if (!dbPassword.get().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "올바른 비밀번호를 입력하세요."); // 401번...
        }

        // 삭제 작업 결과 확인
        int deletedRow = scheduleRepository.deleteSchedule(id);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 id입니다. = " + id);
        }

    }


}
