package peaksoft.springbootlesson.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootlesson.dto.*;
import peaksoft.springbootlesson.entity.User;
import peaksoft.springbootlesson.repository.UserRepository;
import peaksoft.springbootlesson.service.StudentService;
import peaksoft.springbootlesson.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Student Auth", description = "We can create new Student")
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    @PostMapping
    @Operation(summary = "Create", description = "Admin can create new Student")
    public StudentResponse create(@RequestBody StudentRequest request){
        return studentService.registration(request);
    }
    @GetMapping
    @Operation(summary = "Get all students", description = "Only Admin get all Students")
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudent();
    }
    @GetMapping("{id}")
    @Operation(summary = "Get by id", description = "Admin can get Student by id")
    public StudentResponse getStudent(@PathVariable("id")Long id){
        return studentService.getUserById(id);
    }
    @PutMapping("{id}")
    @Operation(summary = "Update", description = "Admin can update Student")
    public StudentResponse update(@PathVariable("id")Long id, @RequestBody StudentRequest request){
        return studentService.update(id, request);
    }
    @DeleteMapping("{id}")
    @Operation(summary = "Delete", description = "Admin can delete Student by id")
    public String delete(@PathVariable("id")Long id){
        studentService.delete(id);
        return "Successfully deleted Student with id: "+id;
    }
    @PutMapping("change-rol/{id}")
    public StudentResponse changeRole(@PathVariable("id")Long id, @RequestBody ChangeRoleRequest request){
        return studentService.changeRole(id, request);
    }


}
