package oat.tryaspectj.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import oat.tryaspectj.entity.Student;
import oat.tryaspectj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Operation(summary = "add student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add student",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))})
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.addStudent(student));
    }
    @Operation(summary = "update student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update student",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))})
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(
            @RequestBody Student student,
            @PathVariable("id") String id
    ){
        return ResponseEntity.ok(studentService.updateStudent(id,student));
    }

    @Operation(summary = "delete student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete student",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))})
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") String id){
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }

    @Operation(summary = "Get All Student in database")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Find All Student",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Student.class))
                    }),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public ResponseEntity<?> showAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @Operation(summary = "Get Student by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "get student by id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Student.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found",
                    content = @Content)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<?> showStudent(@PathVariable("id") String id){
        return ResponseEntity.ok(studentService.getStudent(id));
    }
}
