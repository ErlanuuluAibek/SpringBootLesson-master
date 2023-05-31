package peaksoft.springbootlesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.springbootlesson.dto.*;
import peaksoft.springbootlesson.entity.Group;
import peaksoft.springbootlesson.entity.Role;
import peaksoft.springbootlesson.entity.User;
import peaksoft.springbootlesson.repository.GroupRepository;
import peaksoft.springbootlesson.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final GroupRepository groupRepository;
    public StudentResponse registration(StudentRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.STUDENT);
        Group group = groupRepository.findById(request.getGroupId()).get();
        user.setGroup(group);
        userRepository.save(user);
        return mapToResponse(user);
    }
    public StudentResponse changeRole(Long userId, ChangeRoleRequest request){
        User user = userRepository.findById(userId).get();
        user.setRole(Role.valueOf(request.getRoleName()));
        userRepository.save(user);
        return mapToResponse(user);
    }
    public StudentResponse mapToResponse(User user){
        return StudentResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .groupName(user.getGroup().getGroupName())
                .roleName(user.getRole().name())
                .build();

    }
    public List<StudentResponse> getAll(){
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            studentResponses.add(mapToResponse(user));
        }
        return studentResponses;
    }
    public StudentResponse getUserById(Long userId){
        User user = userRepository.findById(userId).get();
        return mapToResponse(user);
    }

    public StudentResponse update(Long userId, StudentRequest request){
        User user = userRepository.findById(userId).get();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        Group group = groupRepository.findById(request.getGroupId()).get();
        user.setGroup(group);
        userRepository.save(user);
        return mapToResponse(user);
    }
    public String delete(Long studentId){
        userRepository.deleteById(studentId);
        return "Successfully deleted student with id: "+studentId;
    }
    public List<User> getAllStudent(){
        return userRepository.getAllStudents();
    }
}
