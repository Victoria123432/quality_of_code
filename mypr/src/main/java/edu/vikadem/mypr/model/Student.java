package edu.vikadem.mypr.model;


/*
@author admin
@mypr
@class Student
@since 19.04.2025 - 12.01

*/

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Document
public class Student {
    @Id
    private String id;
    private String name;
    private String code;
    private String description;
    private LocalDateTime createDate;
    private List<LocalDateTime> updateDate;


    public Student( String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public Student(String id, String name, String code, String description) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;

        return getId().equals(student.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
