package peaksoft.springbootlesson.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootlesson.entity.Role;

@Getter
@Setter
public class TeacherRequest {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Long courseId;
}
