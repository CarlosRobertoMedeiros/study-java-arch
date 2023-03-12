package br.com.roberto.advanced.advancedtechniques.rest;


import br.com.roberto.advanced.advancedtechniques.entity.Student;
import br.com.roberto.advanced.advancedtechniques.rest.dto.StudentInputDto;
import br.com.roberto.advanced.advancedtechniques.rest.dto.StudentOutputDto;
import br.com.roberto.advanced.advancedtechniques.services.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ){
        try{
            List<Student> students = new ArrayList<>();
            Pageable pagination = PageRequest.of(page,size);
            Page<Student> studentPageable = studentService.getAllStudents(pagination);

            students = studentPageable.getContent();

            Map<String,Object> response = new HashMap<>();
            response.put("students",students);
            response.put("currentPage",studentPageable.getNumber());
            response.put("totalItems",studentPageable.getTotalElements());
            response.put("totalPages",studentPageable.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
