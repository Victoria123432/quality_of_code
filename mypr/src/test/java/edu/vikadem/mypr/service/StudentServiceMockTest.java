package edu.vikadem.mypr.service;

import edu.vikadem.mypr.model.Student;
import edu.vikadem.mypr.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import request.StudentCreateRequest;
import request.StudentUpdateRequest;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/*
@author admin
@mypr
@class ItemServiceMockTest
@since 15.05.2025 - 21.17


*/
@SpringBootTest
public class StudentServiceMockTest {

    @Mock
    private StudentRepository mockRepository;

    private StudentService underTest;

    @Captor
    private ArgumentCaptor<Student> argumentCaptor;

    private StudentCreateRequest request;
    private Student student;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        underTest = new StudentService(mockRepository);
    }
    @AfterEach
    void tearDown() {}

    @DisplayName("Create new Student. Happy Path")
    @Test
    void whenInsertNewStudentAndCodeNotExistsThenOk(){
        request = new StudentCreateRequest("Ariana", "4253", "singer");
        student = Student.builder().name(request.name()).code(request.code()).description(request.description()).build();
        given(mockRepository.existsByCode(request.code())).willReturn(false);
        underTest.create(request);
        then(mockRepository).should().save(argumentCaptor.capture());
        Student studentToSave = argumentCaptor.getValue();
        assertThat(studentToSave.getName()).isEqualTo(request.name());
        assertNotNull(studentToSave.getCreateDate());
        assertTrue(studentToSave.getCreateDate().isBefore(LocalDateTime.now()));
        assertTrue(studentToSave.getUpdateDate().isEmpty());
        verify(mockRepository).save(studentToSave);
        verify(mockRepository, times(1)).existsByCode(request.code());
        verify(mockRepository, times(1)).save(studentToSave);
    }
    @Test
    @DisplayName("Create new Student. Wrong Path")
    void whenInsertNewStudentAndCodeExistsThenNull(){
        request = new StudentCreateRequest("Ariana", "4253", "singer");
        given(mockRepository.existsByCode(request.code())).willReturn(true);
        Student result = underTest.create(request);

        assertNull(result);
        verify(mockRepository, times(1)).existsByCode(request.code());
        verify(mockRepository, never()).save(any());
    }
    @Test
    @DisplayName("Update Student. Happy Path")
    void whenUpdateStudentAndCodeNotExistsThenOk(){
        String studentId = "1";
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        List<LocalDateTime> existingUpdates = new ArrayList<>();

        Student existingStudent = Student.builder()
                .id(studentId)
                .name("Old Name")
                .code("0001")
                .description("Old desc")
                .createDate(createdAt)
                .updateDate(existingUpdates)
                .build();

        StudentUpdateRequest updateRequest = new StudentUpdateRequest(
                studentId, "Ariana", "9999", "superstar"
        );

        given(mockRepository.findById(studentId)).willReturn(Optional.of(existingStudent));
        given(mockRepository.existsByCode(updateRequest.code())).willReturn(false);
        given(mockRepository.save(any(Student.class))).willAnswer(invocation -> invocation.getArgument(0));

        // when
        Student result = underTest.update(updateRequest);

        // then
        assertNotNull(result);
        assertEquals("Ariana", result.getName());
        assertEquals("9999", result.getCode());
        assertEquals("superstar", result.getDescription());
        assertEquals(createdAt, result.getCreateDate());

        assertNotNull(result.getUpdateDate());
        assertEquals(1, result.getUpdateDate().size());
        assertTrue(result.getUpdateDate().get(0).isBefore(LocalDateTime.now()));

        verify(mockRepository).findById(studentId);
        verify(mockRepository).existsByCode(updateRequest.code());
        verify(mockRepository).save(result);
    }

    @Test
    @DisplayName("Update Student. Wrong Path")
    void whenUpdateStudentAndCodeExistsThenNull(){
        // given
        String id = "1";
        StudentUpdateRequest updateRequest = new StudentUpdateRequest(id, "New Name", "4253", "updated desc");

        Student existingStudent = Student.builder()
                .id(id)
                .name("Old Name")
                .code("1234")
                .description("desc")
                .createDate(LocalDateTime.now().minusDays(2))
                .updateDate(new ArrayList<>())
                .build();

        given(mockRepository.findById(id)).willReturn(Optional.of(existingStudent));
        given(mockRepository.existsByCode(updateRequest.code())).willReturn(true);

        // when
        Student result = underTest.update(updateRequest);

        // then
        assertNull(result);
        verify(mockRepository).findById(id);
        verify(mockRepository).existsByCode(updateRequest.code());
        verify(mockRepository, never()).save(any());
    }

    @Test
    @DisplayName("Update Student - student not found")
    void updateStudent_WhenStudentNotFound_ReturnsNull() {
        StudentUpdateRequest updateRequest = new StudentUpdateRequest("nonexistent", "Ariana", "1234", "desc");
        given(mockRepository.findById("nonexistent")).willReturn(Optional.empty());

        Student result = underTest.update(updateRequest);

        assertNull(result);
        verify(mockRepository, never()).save(any());
    }

    @Test
    @DisplayName("Create new Student. Empty Name - should not save")
    void whenCreateStudentWithEmptyNameThenReturnNull() {
        request = new StudentCreateRequest("", "1234", "desc");
        given(mockRepository.existsByCode(request.code())).willReturn(false);

        Student result = underTest.create(request);

        assertNull(result);
        verify(mockRepository, never()).save(any());
    }

    @Test
    @DisplayName("Update Student. Empty Name - should not save")
    void whenUpdateStudentWithEmptyNameThenReturnNull() {
        // given
        String id = "1";
        StudentUpdateRequest updateRequest = new StudentUpdateRequest(id, "", "1234", "updated desc");

        Student existingStudent = Student.builder()
                .id(id)
                .name("Old Name")
                .code("0001")
                .description("Old desc")
                .createDate(LocalDateTime.now().minusDays(1))
                .updateDate(new ArrayList<>())
                .build();

        given(mockRepository.findById(id)).willReturn(Optional.of(existingStudent));
        given(mockRepository.existsByCode(updateRequest.code())).willReturn(false);

        // when
        Student result = underTest.update(updateRequest);

        // then
        assertNull(result);
        verify(mockRepository, never()).save(any());
    }








}
