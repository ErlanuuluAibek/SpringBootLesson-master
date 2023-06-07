package peaksoft.springbootlesson.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.springbootlesson.dto.*;
import peaksoft.springbootlesson.entity.Course;
import peaksoft.springbootlesson.entity.Role;
import peaksoft.springbootlesson.entity.User;
import peaksoft.springbootlesson.repository.CourseRepository;
import peaksoft.springbootlesson.repository.UserRepository;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final UserRepository teacherRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;
    public TeacherResponse create(TeacherRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Course course = courseRepository.findById(request.getCourseId()).get();
        if(course.getTeacher()==null){
            user.setCourse(course);
        }else {
            log.error("Course all ready exist" + course.getTeacher().getFirstName());
            throw new EntityExistsException("Course all ready exist");
        }
        user.setRole(Role.INSTRUCTOR);
        user.setLocalDate(LocalDate.now());
        teacherRepository.save(user);
        return mapToResponse(user);
    }
    public TeacherResponse mapToResponse(User user){
        return TeacherResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .courseName(user.getCourse().getCourseName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleName(user.getRole().name())
                .localDate(user.getLocalDate()).build();

    }
    public TeacherResponse changeRole(Long userId, ChangeRoleRequest request){
        User user = teacherRepository.findById(userId).get();
        user.setRole(Role.valueOf(request.getRoleName()));
        teacherRepository.save(user);
        return mapToResponse(user);
    }
    public List<TeacherResponse> getAll(){
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        for (User user : teacherRepository.findAll()) {
            teacherResponses.add(mapToResponse(user));
        }
        return teacherResponses;
    }
    public TeacherResponse getUserById(Long userId){
        User user = teacherRepository.findById(userId).get();
        return mapToResponse(user);
    }

    public TeacherResponse update(Long userId, TeacherRequest request){
        User user = teacherRepository.findById(userId).get();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Course course = courseRepository.findById(request.getCourseId()).get();
        user.setCourse(course);

        teacherRepository.save(user);
        return mapToResponse(user);
    }
    public String delete(Long teacherId){
        teacherRepository.deleteById(teacherId);
        return "Successfully deleted user with id: "+teacherId;
    }
    public List<TeacherResponse> getAllTeacher (){
        List<User> users = teacherRepository.findAll();
        List<User> teachers = new ArrayList<>();
        for(User user : users){
            if(user.getRole().getAuthority().equals("INSTRUCTOR")){
                teachers.add(user);
            }
        }
        List<TeacherResponse> responses = new ArrayList<>();
        for (User user : teachers){
            responses.add(mapToResponse(user));
        }
        return responses;
    }

}
