package com.example.calendar.repository;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Annotation @Repository는 @Component와 같다, Spring Bean으로 등록한다는 뜻.
 * Spring Bean으로 등록되면 다른 클래스에서 주입하여 사용할 수 있다.
 * 명시적으로 Repository Layer 라는것을 나타낸다.
 * DB와 상호작용하여 데이터를 CRUD하는 작업을 수행한다.
 */
@Repository
public class JdbcScheduleRepository implements ScheduleRepository {
    // 데이터 접근을 위한 JdbcTemplate 인스턴스
    private final JdbcTemplate jdbcTemplate;

    // DataSource로 JdbcTemplate 초기화
    public JdbcScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        // schedule 테이블에 데이터 삽입 및 자동 생성된 Id 가져오기
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id")
                .usingColumns("user_name", "password", "title", "contents");

        // dto의 필드 값을 Map으로 변환하여 피라미터로 전달
        Map<String, Object> parameters = new HashMap<>(); // 받아온 정보를 Map으로 바꿔줌.
        parameters.put("user_name", dto.getUserName());
        parameters.put("password", dto.getPassword());
        parameters.put("title", dto.getTitle());
        parameters.put("contents", dto.getContents());

        // 생성된 Id를 사용해 데이터 조회 후 반환
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return findScheduleById(key.longValue()).get();
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(String userName, String updatedAt) {
        // 조건(userName, updatedAt)에 따라 일정 목록을 조회
        List<ScheduleResponseDto> result = jdbcTemplate.query("SELECT * FROM schedule WHERE user_name = ? OR DATE(updated_at) = DATE(?) ORDER BY updated_at", scheduleRowMapper(), userName, updatedAt);
        return result;
    }

    @Override
    public Optional<ScheduleResponseDto> findScheduleById(Long id) {
        // 조건(userName, updatedAt)에 따라 일정 목록을 조회
        List<ScheduleResponseDto> result = jdbcTemplate.query("SELECT * FROM schedule WHERE id = ?", scheduleRowMapper(), id);
        // findAny()로 스트림에서 순서 상관없이 임의로 선택된 하나의 값을 반환, null인 경우 조회되는 메모가 없을 수도 있기 때문에, findAny()가 Optional 형태로 메모를 만들어서 반환
        return result.stream().findAny();
    }

    // ResultSet 데이터를 ScheduleResponseDto 객체로 변환하여 반환
    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("user_name"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
                );
            }
        };
    }

    @Override
    public Optional<String> findPasswordById(Long id) {
        // id에 해당하는 일정의 비밀번호를 조회
        return jdbcTemplate.query("SELECT password FROM schedule WHERE id = ?", scheduleRowMapperV2(), id).stream().findAny();
    }

    @Override
    public int updateSchedule(Long id, ScheduleRequestDto dto) {
        // 주어진 id의 일정 데이터를 수정하고, DB 연산에서 영향을 받은 데이터 수 반환
        return jdbcTemplate.update("UPDATE schedule SET user_name = ?, title = ?, contents = ? WHERE id = ?", dto.getUserName(), dto.getTitle(), dto.getContents(), id);
    }

    @Override
    public int deleteSchedule(Long id) {
        // 주어진 id의 일정 데이터를 삭제하고,  DB 연산에서 영향을 받은 데이터 수 반환
        return jdbcTemplate.update("DELETE FROM schedule WHERE id = ?", id);
    }

    // ResultSet에서 비밀번호를 추출하여 반환하는 RowMapper
    private RowMapper<String> scheduleRowMapperV2() {
        return new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("password");
            }
        };
    }
}