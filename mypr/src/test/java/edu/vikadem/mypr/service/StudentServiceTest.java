package edu.vikadem.mypr.service;

import edu.vikadem.mypr.model.Student;
import edu.vikadem.mypr.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import request.StudentCreateRequest;
import request.StudentUpdateRequest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
@author admin
@mypr
@class StudentServiceTest
@since 30.04.2025 - 22.22

*/
@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentService underTest;

    @BeforeEach
    void setUp() {
    }

    //@AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateStudentWithId() {
        StudentCreateRequest request = new StudentCreateRequest("Marina", "0006", "singer");
        Student student = underTest.create(request);

        assertNotNull(student);
        assertNotNull(student.getId());
    }

    @Test
    void shouldCreateStudentWithCorrectFields() {
        StudentCreateRequest request = new StudentCreateRequest("Marina", "0006", "singer");
        Student student = underTest.create(request);

        assertEquals("Marina", student.getName());
        assertEquals("0006", student.getCode());
        assertEquals("singer", student.getDescription());
    }

    @Test
    void shouldSetCreateDateToCurrentTimeOrLater() {
        StudentCreateRequest request = new StudentCreateRequest("Marina", "0006", "singer");
        LocalDateTime beforeCreate = LocalDateTime.now();
        Student student = underTest.create(request);

        assertNotNull(student.getCreateDate());
        assertFalse(student.getCreateDate().isBefore(beforeCreate));
    }

    @Test
    void shouldHaveValidCreateDateType() {
        Student student = underTest.create(new StudentCreateRequest("Marina", "0006", "singer"));

        assertSame(LocalDateTime.class, student.getCreateDate().getClass());
    }

    @Test
    void shouldInitializeUpdateDateAsEmptyList() {
        Student student = underTest.create(new StudentCreateRequest("Marina", "0006", "singer"));

        assertNotNull(student.getUpdateDate());
        assertSame(ArrayList.class, student.getUpdateDate().getClass());
        assertTrue(student.getUpdateDate().isEmpty());
    }

    @Test
    void shouldUpdateExistingStudentFields() {
        // given
        Student existing = underTest.create(new StudentCreateRequest("Olga", "0007", "math teacher"));
        StudentUpdateRequest updateRequest = new StudentUpdateRequest(
                existing.getId(), "Olga Updated", "0008", "physics teacher"
        );

        // when
        Student updated = underTest.update(updateRequest);

        // then
        assertNotNull(updated);
        assertEquals(existing.getId(), updated.getId());
        assertEquals("Olga Updated", updated.getName());
        assertEquals("0008", updated.getCode());
        assertEquals("physics teacher", updated.getDescription());
    }

    @Test
    void shouldPreserveCreateDateOnUpdate() {
        Student existing = underTest.create(new StudentCreateRequest("Nina", "0009", "student"));
        LocalDateTime originalCreateDate = existing.getCreateDate();

        StudentUpdateRequest updateRequest = new StudentUpdateRequest(
                existing.getId(), "Nina 2", "0010", "updated"
        );

        Student updated = underTest.update(updateRequest);

        assertNotNull(updated.getCreateDate());

        assertEquals(
                originalCreateDate.truncatedTo(ChronoUnit.MILLIS),
                updated.getCreateDate().truncatedTo(ChronoUnit.MILLIS)
        );
    }

    @Test
    void shouldAddTimestampToUpdateDateList() {
        Student existing = underTest.create(new StudentCreateRequest("Iryna", "0011", "lawyer"));
        int previousSize = existing.getUpdateDate().size();

        StudentUpdateRequest updateRequest = new StudentUpdateRequest(
                existing.getId(), "Iryna Updated", "0012", "updated lawyer"
        );

        Student updated = underTest.update(updateRequest);

        assertEquals(previousSize + 1, updated.getUpdateDate().size());
        assertTrue(updated.getUpdateDate().get(updated.getUpdateDate().size() - 1).isAfter(existing.getCreateDate()));
    }

    @Test
    void shouldReturnNullIfStudentNotFound() {
        StudentUpdateRequest updateRequest = new StudentUpdateRequest(
                "non-existent-id", "Ghost", "0000", "nothing"
        );

        Student result = underTest.update(updateRequest);

        assertNull(result);
    }

    @Test
    void shouldReturnNonNullStudentWhenCreated() {
        StudentCreateRequest request = new StudentCreateRequest("Marina", "0006", "singer");
        Student student = underTest.create(request);

        assertNotNull(student);
    }

    @Test
    void shouldReturnNullWhenUpdatingNonExistingStudent() {
        StudentUpdateRequest updateRequest = new StudentUpdateRequest(
                "non-existent-id", "Ghost", "0000", "nothing"
        );

        Student result = underTest.update(updateRequest);
        assertNull(result);
    }

    @Test
    void shouldReturnStudentByIdWithGetById() {
        Student created = underTest.create(new StudentCreateRequest("C", "1003", "descC"));

        Student fetched = underTest.getById(created.getId());

        assertNotNull(fetched);
        assertEquals(created.getId(), fetched.getId());
    }

    @Test
    void shouldReturnNullWhenGetByIdNotFound() {
        Student fetched = underTest.getById("absent-id");
        assertNull(fetched);
    }

    @Test
    void shouldDeleteStudentById() {
        Student created = underTest.create(new StudentCreateRequest("E", "1005", "descE"));
        underTest.delById(created.getId());

        assertNull(underTest.getById(created.getId()));
    }










    @Test
    void update() {
    }
}