package edu.vikadem.mypr.repository;

import edu.vikadem.mypr.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
@author admin
@mypr
@class StudentRepository
@since 19.04.2025 - 12.49


*/
@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}
