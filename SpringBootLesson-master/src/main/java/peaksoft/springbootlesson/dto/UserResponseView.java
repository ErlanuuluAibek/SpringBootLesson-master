package peaksoft.springbootlesson.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootlesson.entity.User;

import java.util.List;

@Getter
@Setter
public class UserResponseView {
    private List<UserResponse> userResponses;
}
