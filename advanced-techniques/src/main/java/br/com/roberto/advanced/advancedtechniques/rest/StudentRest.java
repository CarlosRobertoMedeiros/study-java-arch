package br.com.roberto.advanced.advancedtechniques.rest;


import br.com.roberto.advanced.advancedtechniques.entity.Student;
import br.com.roberto.advanced.advancedtechniques.rest.dto.StudentInputDto;
import br.com.roberto.advanced.advancedtechniques.rest.dto.StudentOutputDto;
import br.com.roberto.advanced.advancedtechniques.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentRest {

    private final StudentService studentService;

    public StudentRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentOutputDto>> getAllStudents() {
        if (studentService.getAllStudents().size() > 0) {
            List<StudentOutputDto> studentsOutputDto = new ArrayList<>();
            studentService.getAllStudents().stream().forEach(student -> {
                StudentOutputDto studentOutputDto = new StudentOutputDto(student.getId(), student.getFirstName(), student.getSecondName());
                studentsOutputDto.add(studentOutputDto);
            });
            return ResponseEntity.ok(studentsOutputDto);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudent(@RequestBody StudentInputDto studentInputDto) {
        Student studentDataInput = new Student(studentInputDto.firstName(), studentInputDto.secondName());
        Student studentDataOutput = studentService.addStudent(studentDataInput);
        if (studentDataOutput.getFirstName() != null) {
            return ResponseEntity.ok(new StudentOutputDto(studentDataOutput.getId(), studentDataOutput.getFirstName(), studentDataOutput.getSecondName()));
        }
        return ResponseEntity.noContent().build();
    }
}
