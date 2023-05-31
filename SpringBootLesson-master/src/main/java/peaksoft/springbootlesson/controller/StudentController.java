package peaksoft.springbootlesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootlesson.dto.*;
import peaksoft.springbootlesson.entity.User;
import peaksoft.springbootlesson.service.StudentService;
import peaksoft.springbootlesson.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;
    private final UserService userService;
    @GetMapping()
    public List<StudentResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("{id}")
    public StudentResponse getById(@PathVariable("id") Long userId){
        return service.getUserById(userId);
    }
    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest request){
        return service.registration(request);
    }
    @PutMapping("{id}")
    public StudentResponse update(@PathVariable("id")Long userId, @RequestBody StudentRequest request){
        return service.update(userId,request);
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Long studentId){
        return service.delete(studentId);
    }
    @PutMapping("change-role/{id}")
    public StudentResponse changeRole(@PathVariable("id") Long id,@RequestBody ChangeRoleRequest request){
        return service.changeRole(id,request);
    }
//    @GetMapping
//    public UserResponseView getAllUsers(@RequestParam(name = "text",required = false)String text,
//                                        @RequestParam int page,
//                                        @RequestParam int size){
//        return userService.searchAndPagination(text,page,size);
//    }

}
