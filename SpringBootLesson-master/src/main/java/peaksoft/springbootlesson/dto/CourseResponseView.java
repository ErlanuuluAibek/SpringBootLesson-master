package peaksoft.springbootlesson.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootlesson.entity.Course;

import java.util.List;
@Getter
@Setter
public class CourseResponseView {
    private List<CourseResponse> courseResponses;
}
