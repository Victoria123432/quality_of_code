package edu.vikadem.mypr;

/*
@author admin
@mypr
@class RepositoryTest
@since 24.04.2025 - 18.24

*/


import edu.vikadem.mypr.model.Student;
import edu.vikadem.mypr.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class RepositoryTest {
    @Autowired
    StudentRepository underTest;

    @BeforeAll
    static void beforeAll() {}

    @BeforeEach
    void setUp() {
        Student vira = new Student("1", "Vira", "123", "###test");
        Student rina = new Student("2", "Rina", "456", "###test");
        Student lara = new Student("3", "Lara", "789", "###test");
        underTest.saveAll(List.of(vira, rina, lara));
    }
    @AfterEach
    void tearDown() {
        List<Student> studentsToDelete = underTest.findAll().stream()
                .filter(student -> student.getDescription().contains("###test"))
                .toList();
        underTest.deleteAll(studentsToDelete);
    }

    @AfterAll
    static void afterAll() {}

    @Test
    void testSetShouldContains_3_Records_ToTest(){
        List<Student> studentsToDelete = underTest.findAll().stream()
                .filter(item -> item.getDescription().contains("###test"))
                .toList();
        assertEquals(3,studentsToDelete.size());
    }

    @Test
    void shouldGiveIdForNewRecord() {
        // given
        Student zina = new Student("Zina", "324", "###test");
        // when
        underTest.save(zina);
        Student studentFromDb = underTest.findAll().stream()
                .filter(item -> item.getName().equals("Zina"))
                .findFirst().orElse(null);
        // then
        assertFalse(studentFromDb.getId() == zina.getId());
        assertNotNull(studentFromDb);
        assertNotNull(studentFromDb.getId());
        assertFalse(studentFromDb.getId().isEmpty());
        assertEquals(24, studentFromDb.getId().length());
    }

    @Test
    void whenRecordHasIdThenItIsPossibleToSave() {
        // given
        Student zina = Student.builder()
                .id("1")
                .description("###test2")
                .build();
        // when
        underTest.save(zina);
        Student studentFromDb = underTest.findAll().stream()
                .filter(item -> item.getDescription().equals("###test2"))
                .findFirst().orElse(null);
        // then

        assertNotNull(studentFromDb);
    }

    @Test
    void shouldDeleteStudentById() {
        // given
        String studentId = "1";

        // when
        underTest.deleteById(studentId);
        Optional<Student> studentOptional = underTest.findById(studentId);

        // then
        assertTrue(studentOptional.isEmpty());
    }

    @Test
    void shouldUpdateExistingStudent() {
        // given
        Student existingStudent = underTest.findById("2").orElseThrow();
        String updatedCode = "999";
        existingStudent.setCode(updatedCode);

        // when
        underTest.save(existingStudent);
        Student updatedStudent = underTest.findById("2").orElseThrow();

        // then
        assertEquals(updatedCode, updatedStudent.getCode());
        assertEquals("Rina", updatedStudent.getName());
    }

    @Test
    void shouldFindStudentByCode() {
        // given
        String codeToFind = "456";

        // when
        List<Student> studentsWithCode = underTest.findAll().stream()
                .filter(student -> student.getCode().equals(codeToFind))
                .toList();

        // then
        assertEquals(1, studentsWithCode.size());
        assertEquals("Rina", studentsWithCode.get(0).getName());
    }

    @Test
    void shouldCountAllTestStudents() {
        // when
        long count = underTest.findAll().stream()
                .filter(student -> student.getDescription().contains("###test"))
                .count();

        // then
        assertEquals(3, count);
    }

    @Test
    void shouldCheckExistenceById() {
        // given
        String existingId = "2";
        String nonExistingId = "999";

        // when
        boolean exists = underTest.existsById(existingId);
        boolean notExists = underTest.existsById(nonExistingId);

        // then
        assertTrue(exists);
        assertFalse(notExists);
    }

    @Test
    void shouldFindAllStudentsSortedByName() {
        // when
        List<Student> sortedStudents = underTest.findAll().stream()
                .filter(student -> student.getDescription().contains("###test"))
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
                .toList();

        // then
        assertEquals(3, sortedStudents.size());
        assertEquals("Lara", sortedStudents.get(0).getName());
        assertEquals("Rina", sortedStudents.get(1).getName());
        assertEquals("Vira", sortedStudents.get(2).getName());
    }

    @Test
    void shouldFindStudentByName() {
        // given
        String studentName = "Vira";

        // when
        List<Student> found = underTest.findAll().stream()
                .filter(student -> student.getName().equals(studentName))
                .toList();

        // then
        assertEquals(1, found.size());
        assertEquals(studentName, found.get(0).getName());
        assertEquals("123", found.get(0).getCode());
    }
}
