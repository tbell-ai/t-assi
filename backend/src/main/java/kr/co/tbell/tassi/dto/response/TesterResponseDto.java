package kr.co.tbell.tassi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class TesterResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TestertId {
        private Long id;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TesterData {
        private Long id;
        private String testerName;
        private String testerDepartment;
        private String testerAge;
        private String testerFinalSchool;
        private String testerFinalCareer;
        private String testerStrength;
        private LocalDateTime createDate;
        private LocalDateTime modifiedDate;
    }
}
