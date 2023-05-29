package peaksoft.springbootlesson.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class GroupResponse {
    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;
}
