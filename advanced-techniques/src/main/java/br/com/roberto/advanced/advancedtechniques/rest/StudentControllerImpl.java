package br.com.roberto.advanced.advancedtechniques.rest;


import br.com.roberto.advanced.advancedtechniques.entity.Student;
import br.com.roberto.advanced.advancedtechniques.rest.documentation.StudentController;
import br.com.roberto.advanced.advancedtechniques.rest.dto.StudentInputDto;
import br.com.roberto.advanced.advancedtechniques.rest.dto.StudentOutputDto;
import br.com.roberto.advanced.advancedtechniques.services.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class StudentControllerImpl implements StudentController {

    private final StudentService studentService;

    public StudentControllerImpl(StudentService studentService) {
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

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<Student> students = new ArrayList<>();
            Pageable pagination = PageRequest.of(page, size);
            Page<Student> studentPageable = studentService.getAllStudents(pagination);

            students = studentPageable.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("students", students);
            response.put("currentPage", studentPageable.getNumber());
            response.put("totalItems", studentPageable.getTotalElements());
            response.put("totalPages", studentPageable.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentOutputDto> getSudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);

        if (student.isPresent()) {
            StudentOutputDto studentOutputDto = new StudentOutputDto(student.get().getId(),
                    student.get().getFirstName(),
                    student.get().getSecondName());
            return new ResponseEntity<>(studentOutputDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<StudentOutputDto> updateStudent(@PathVariable Long id, @RequestBody StudentInputDto studentInputDto) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Student studentInteno = studentService.updateStudent(new Student(student.get().getId(),
                studentInputDto.firstName(),
                studentInputDto.secondName()));
        if (null != studentInteno) {
            return ResponseEntity.ok(new StudentOutputDto(studentInteno.getId(), studentInteno.getFirstName(), studentInteno.getSecondName()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StudentOutputDto> deleteStudent(@PathVariable Long id){

        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
