package peaksoft.springbootlesson.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootlesson.dto.CompanyRequest;
import peaksoft.springbootlesson.dto.CompanyResponse;
import peaksoft.springbootlesson.dto.CourseRequest;
import peaksoft.springbootlesson.dto.CourseResponse;
import peaksoft.springbootlesson.entity.Course;
import peaksoft.springbootlesson.service.CourseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;
    @PostMapping
    public CourseResponse create(@RequestBody CourseRequest request){
        return  courseService.create(request);
    }
    @GetMapping()
    public List<CourseResponse> getAll(){
        return courseService.getAllCourses();
    }
    @GetMapping("{id}")
    public CourseResponse getCourseById(@PathVariable("id")Long id){
        return courseService.getCourseById(id);
    }
    @PutMapping("{id}")
    public CourseResponse update(@PathVariable("id")Long id,@RequestBody CourseRequest request){
        return courseService.updateCourse(id,request);
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Long id){
        courseService.deleteCourse(id);
        return "Successfully deleted course with id: "+id;
    }
}
