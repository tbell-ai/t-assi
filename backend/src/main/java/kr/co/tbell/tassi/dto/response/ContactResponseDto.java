package kr.co.tbell.tassi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ContactResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ContactId {
        private Long id;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ContactData {
        private Long userId;
        private Long contactId;
        private String contactServiceName;
        private String contactServiceType;
        private String contactTestType;
        private String contactStart;
        private String contactEnd;
        private String contactContent;
        private String contactState;
        private String userName;
        private String userEmail;
        private LocalDateTime createDate;
        private LocalDateTime modifiedDate;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ContactListData {
        private Long contactId;
        private String contactServiceName;
        private String contactTestType;
        private String contactState;
        private String userName;
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
    public static class Contact {
        private List<ContactResponseDto.ContactListData> contactData;
        private ContactResponseDto.Paging paging;
    }
}
