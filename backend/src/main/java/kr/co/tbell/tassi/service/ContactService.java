package kr.co.tbell.tassi.service;

import kr.co.tbell.tassi.dto.Response;
import kr.co.tbell.tassi.dto.request.CommentRequestDto;
import kr.co.tbell.tassi.dto.request.ContactRequestDto;
import kr.co.tbell.tassi.dto.response.CommentResponseDto;
import kr.co.tbell.tassi.dto.response.ContactResponseDto;
import kr.co.tbell.tassi.entity.Contact;
import kr.co.tbell.tassi.entity.ContactComment;
import kr.co.tbell.tassi.entity.Users;
import kr.co.tbell.tassi.repository.ContactCommentRepository;
import kr.co.tbell.tassi.repository.ContactRepository;
import kr.co.tbell.tassi.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactCommentRepository contactCommentRepository;
    private final UsersRepository usersRepository;
    private final Response response;

    public ResponseEntity<?> createContact(ContactRequestDto.Create data) {

        Users user = usersRepository.getById(data.getUserId());

        Contact contact = Contact.builder()
                .contactServiceName(data.getContactServiceName())
                .contactServiceType(data.getContactServiceType())
                .contactTestType(data.getContactTestType())
                .contactStart(data.getContactStart())
                .contactEnd(data.getContactEnd())
                .contactContent(data.getContactContent())
                .contactState("답변대기")
                .user(user)
                .build();

        contactRepository.save(contact);

        return response.success("의뢰 작성에 성공했습니다.");
    }

    public ResponseEntity<?> createComment(CommentRequestDto.Create data) {

        Contact cntact = contactRepository.getById(data.getTargetId());
        Users user = usersRepository.getById(data.getUserId());

        ContactComment comment = ContactComment.builder()
                .comment(data.getComment())
                .contact(cntact)
                .user(user)
                .build();

        contactCommentRepository.save(comment);

        return response.success("의뢰 댓글 작성에 성공했습니다.");
    }

    public ResponseEntity<?> updateComment(CommentRequestDto.Update data) {

        Contact cntact = contactRepository.getById(data.getTargetId());
        Users user = usersRepository.getById(data.getUserId());

        ContactComment comment = ContactComment.builder()
                .idx(data.getId())
                .comment(data.getComment())
                .contact(cntact)
                .user(user)
                .build();

        contactCommentRepository.save(comment);

        return response.success("의뢰 댓글 수정에 성공했습니다.");
    }

    public ResponseEntity<?> getCommentList(Long id) {

        List<ContactComment> comments = contactCommentRepository.findByContactIdx(id);
        List<CommentResponseDto.CommentListData> commentList = new ArrayList<>();

        for(ContactComment comment : comments) {
            CommentResponseDto.CommentListData dto = CommentResponseDto.CommentListData.builder()
                    .id(comment.getIdx())
                    .comment(comment.getComment())
                    .writer(comment.getUser().getName())
                    .createDate(comment.getCreateDate())
                    .build();

            commentList.add(dto);
        }

        return response.success(commentList);
    }

    public ResponseEntity<?> getContactList(Long id) {

        List<Contact> contacts = contactRepository.findByUserIdx(id);
        List<ContactResponseDto.ContactData> contactList = new ArrayList<>();

        for(Contact contact : contacts) {
            ContactResponseDto.ContactData dto = ContactResponseDto.ContactData.builder()
                    .contactId(contact.getIdx())
                    .contactServiceName(contact.getContactServiceName())
                    .contactServiceType(contact.getContactServiceType())
                    .contactTestType(contact.getContactTestType())
                    .contactStart(contact.getContactStart())
                    .contactEnd(contact.getContactEnd())
                    .contactContent(contact.getContactContent())
                    .contactState(contact.getContactState())
                    .userName(contact.getUser().getName())
                    .userEmail(contact.getUser().getEmail())
                    .createDate(contact.getCreateDate())
                    .modifiedDate(contact.getModifiedDate())
                    .build();

            contactList.add(dto);
        }

        return response.success(contactList);
    }

    public ResponseEntity<?> getAdminContactList(String serviceName, String testType, String serviceType, String state, int pageNum) {

        Page<Contact> contacts = contactRepository.findAllSearch(serviceName, testType, serviceType, state, PageRequest.of(pageNum, 10, Sort.by("create_date").descending()));
        List<ContactResponseDto.ContactListData> contactList = new ArrayList<>();

        for(Contact contact : contacts) {

            ContactResponseDto.ContactListData dto = ContactResponseDto.ContactListData.builder()
                    .contactId(contact.getIdx())
                    .contactServiceName(contact.getContactServiceName())
                    .contactTestType(contact.getContactTestType())
                    .contactState(contact.getContactState())
                    .userName(contact.getUser().getName())
                    .createDate(contact.getCreateDate())
                    .build();

            contactList.add(dto);
        }

        ContactResponseDto.Paging page = ContactResponseDto.Paging.builder()
                .pageNumber(contacts.getNumber())
                .pageSize(contacts.getSize())
                .totalPages(contacts.getTotalPages())
                .numberOfElements(contacts.getNumberOfElements())
                .prev(contacts.hasPrevious())
                .next(contacts.hasNext())
                .last(contacts.isLast())
                .first(contacts.isFirst())
                .empty(contacts.isEmpty())
                .build();

        ContactResponseDto.Contact data = ContactResponseDto.Contact.builder().contactData(contactList).paging(page).build();

        return response.success(data);
    }

    public ResponseEntity<?> getContact(Long id) {
        Optional<Contact> item = contactRepository.findById(id);

        if(item.isPresent()) {
            Contact contact = item.get();

            ContactResponseDto.ContactData dto = ContactResponseDto.ContactData.builder()
                    .userId(contact.getUser().getIdx())
                    .contactId(contact.getIdx())
                    .contactServiceName(contact.getContactServiceName())
                    .contactServiceType(contact.getContactServiceType())
                    .contactTestType(contact.getContactTestType())
                    .contactStart(contact.getContactStart())
                    .contactEnd(contact.getContactEnd())
                    .contactContent(contact.getContactContent())
                    .contactState(contact.getContactState())
                    .userName(contact.getUser().getName())
                    .userEmail(contact.getUser().getEmail())
                    .createDate(contact.getCreateDate())
                    .modifiedDate(contact.getModifiedDate())
                    .build();

            return response.success(dto);
        }

        return null;
    }

    public ResponseEntity<?> updateContact(ContactRequestDto.Update data) {

        Users user = usersRepository.getById(data.getUserId());

        Contact contact = Contact.builder()
                .idx(data.getContactId())
                .contactServiceName(data.getContactServiceName())
                .contactServiceType(data.getContactServiceType())
                .contactTestType(data.getContactTestType())
                .contactStart(data.getContactStart())
                .contactEnd(data.getContactEnd())
                .contactContent(data.getContactContent())
                .contactState(data.getContactState())
                .user(user)
                .build();

        contactRepository.save(contact);

        return response.success("의뢰 수정에 성공하였습니다.");
    }

    public ResponseEntity<?> updateContactState(Long id, String state) {
        int result = contactRepository.updateState(id, state);

        if(result == 1) {
            return response.success("의뢰 상태 수정에 성공하였습니다.");
        }

        return response.success("의뢰 상태 수정에 실패하였습니다.");
    }

    public ResponseEntity<?> getContactState(Long id) {
        Map<String, String> result = new HashMap<>();
        result.put("state", contactRepository.findStateById(id));

        return response.success(result);
    }

    public ResponseEntity<?>  deleteContact(Long id) {
        contactRepository.deleteById(id);

        return response.success("의뢰 삭제에 성공하였습니다.");
    }
}
