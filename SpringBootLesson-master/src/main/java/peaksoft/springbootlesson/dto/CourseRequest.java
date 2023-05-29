package peaksoft.springbootlesson.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootlesson.entity.Company;

@Getter
@Setter
public class CourseRequest {
    private String courseName;
    private String durationMonth;
    private Long  companyId;
}
