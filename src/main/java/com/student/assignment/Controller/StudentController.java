package com.student.assignment.Controller;


import com.student.assignment.Entities.Student;
import com.student.assignment.Model.ErrorResponse;
import com.student.assignment.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "StudentController", description = "Perform CRUD operations on students")
@Validated
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Operation(
            summary = "Save student object in database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student added successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PostMapping("/student/")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student s) {
        try {
            Student student = studentService.createStudent(s);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        } catch (ConstraintViolationException e) {
            ErrorResponse errorResponse = new ErrorResponse("Invalid input");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Operation(
            summary = "Retrieve all students from database"
    )
    @GetMapping("/student/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Students found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No student found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))})
    })
    public ResponseEntity<?> getAllStudents() {
        List<Student> list = studentService.getAllStudents();
        if (list.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("No student found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Retrieve student from database by student id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Student found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Student not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))}),
    })
    @GetMapping("student/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("Student not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.ok(student);
    }

    @Operation(
            summary = "Update student in database by student id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Student Updated Successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Student not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))}),
    })
    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody Student s) {
        Optional<Student> studentOptional = studentService.getStudentById(id);
        if (studentOptional.isPresent()) {
            Student updatedStudent = studentService.updateStudent(id, s);
            return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
        }
        ErrorResponse errorResponse = new ErrorResponse("Student not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @Operation(
            summary = "Delete student from database by student id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Student deleted Successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Student not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))}),
    })
    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("id") int id) {
        Optional<Student> studentOptional = studentService.getStudentById(id);
        if (studentOptional.isPresent()) {
            studentService.deleteStudentById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        ErrorResponse errorResponse = new ErrorResponse("Student not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @Operation(
            summary = "Delete all students from database"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "All students deleted Successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No student found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))}),
    })
    @DeleteMapping("/student/")
    public ResponseEntity<?> deleteAllStudents() {
        List<Student> list = studentService.getAllStudents();
        if (list.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("No Student found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        studentService.deleteAllStudent();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
