package peaksoft.springbootlesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootlesson.dto.*;
import peaksoft.springbootlesson.service.TeacherService;
import peaksoft.springbootlesson.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService service;
    private final UserService userService;
    @GetMapping()
    public List<TeacherResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("{id}")
    public TeacherResponse getById(@PathVariable("id") Long userId){
        return service.getUserById(userId);
    }
    @PostMapping
    public TeacherResponse create(@RequestBody TeacherRequest request){
        return service.create(request);
    }
    @PutMapping("{id}")
    public TeacherResponse update(@PathVariable("id")Long userId, @RequestBody TeacherRequest request){
        return service.update(userId,request);
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Long teacherId){
        return service.delete(teacherId);
    }
    @PutMapping("change-role/{id}")
    public TeacherResponse changeRole(@PathVariable("id") Long id,@RequestBody ChangeRoleRequest request){
        return service.changeRole(id,request);
    }
    @GetMapping("all")
    public List<TeacherResponse> getAllTeachers(){
        return service.getAllTeacher();
    }

}
