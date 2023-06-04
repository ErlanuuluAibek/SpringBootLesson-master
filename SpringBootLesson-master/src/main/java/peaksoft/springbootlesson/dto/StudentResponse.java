package peaksoft.springbootlesson.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootlesson.entity.Group;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class  StudentResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String groupName;
    private String roleName;
    private LocalDate localDate;
}
