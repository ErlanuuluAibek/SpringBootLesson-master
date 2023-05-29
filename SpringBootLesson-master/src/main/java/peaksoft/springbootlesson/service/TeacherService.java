package peaksoft.springbootlesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.springbootlesson.dto.*;
import peaksoft.springbootlesson.entity.Role;
import peaksoft.springbootlesson.entity.User;
import peaksoft.springbootlesson.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final UserRepository teacherRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public TeacherResponse create(TeacherRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRoleName()));
        user.setCreatedDate(LocalDate.now());
        teacherRepository.save(user);
        return mapToResponse(user);
    }
    public TeacherResponse mapToResponse(User user){
        return TeacherResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleName(user.getRole().name())
                .localDate(user.getCreatedDate()).build();

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
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRoleName()));
        teacherRepository.save(user);
        return mapToResponse(user);
    }
    public String delete(Long teacherId){
        teacherRepository.deleteById(teacherId);
        return "Successfully deleted user with id: "+teacherId;
    }

}
