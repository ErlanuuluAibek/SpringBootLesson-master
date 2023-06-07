package peaksoft.springbootlesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.springbootlesson.dto.*;
import peaksoft.springbootlesson.entity.Company;
import peaksoft.springbootlesson.entity.Role;
import peaksoft.springbootlesson.entity.User;
import peaksoft.springbootlesson.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRoleName()));
        user.setLocalDate(LocalDate.now());
        userRepository.save(user);
        return mapToResponse(user);
    }
    public UserResponse mapToResponse(User user){
      return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleName(user.getRole().name())
                .localDate(user.getLocalDate()).build();

    }
    public UserResponse changeRole(Long userId, ChangeRoleRequest request){
        User user = userRepository.findById(userId).get();
        user.setRole(Role.valueOf(request.getRoleName()));
        userRepository.save(user);
        return mapToResponse(user);
    }
    public List<UserResponse> getAll(){
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userResponses.add(mapToResponse(user));
        }
        return userResponses;
    }
    public UserResponse getUserById(Long userId){
        User user = userRepository.findById(userId).get();
        return mapToResponse(user);
    }

    public UserResponse update(Long userId,UserRequest request){
        User user = userRepository.findById(userId).get();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return mapToResponse(user);
    }
    public String delete(Long userId){
        userRepository.deleteById(userId);
        return "Successfully deleted user with id: "+userId;
    }
    public List<UserResponse> view (List<User>users){
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user :users){
            userResponses.add(mapToResponse(user));
        }
        return userResponses;
    }
    public UserResponseView searchAndPagination(String text, int page, int size){
        Pageable pageable= PageRequest.of(page-1,size);
        UserResponseView responseView = new UserResponseView();
        responseView.setUserResponses(view(search(text,pageable)));
        return responseView;
    }

    private List<User> search(String text, Pageable pageable){
        String name = text ==null?"": text;
        return userRepository.searchAndPagination(name.toUpperCase(), pageable);
    }


}
