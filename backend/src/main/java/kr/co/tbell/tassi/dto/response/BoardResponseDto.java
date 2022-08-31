package kr.co.tbell.tassi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class BoardId {
        private Long id;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class BoardData {
        private Long id;
        private Long projectId;
        private Long userId;
        private String subject;
        private String writer;
        private String type;
        private String content;
        private String state;
        private LocalDateTime createDate;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class BoardListData {
        private Long id;
        private String subject;
        private String type;
        private String writer;
        private String state;
        private LocalDateTime createDate;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Paging {
        // 현재 페이지 크기
        private Integer pageSize;

        // 현재 페이지 정보
        private Integer pageNumber;

        // 전체 페이지 수
        private Integer totalPages;

        // 결과 데이터 수
        private Integer numberOfElements;

        // 마지막 페이지 여부
        private boolean last;

        // 첫번째 페이지 여부
        private boolean first;

        // 빈 페이지 여부
        private boolean empty;

        // 이전 페이지 존재 여부
        private boolean prev;

        // 다음 페이지 존재 여부
        private boolean next;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Board {
        private List<BoardResponseDto.BoardListData> boardData;
        private BoardResponseDto.Paging paging;
    }
}
