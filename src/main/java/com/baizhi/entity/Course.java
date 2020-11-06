package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private Integer id;
    @NotNull
    private Integer subjectId;
    @NotBlank
    private String teacherName;
    @Min(1)
    @Max(6)
    private Integer dayOfWeek;
    @Min(1)
    @Max(8)
    private Integer lessonNo;
    private Integer status;
    private Integer operation;
    private Integer originId;
    private Integer studentCpy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(subjectId, course.subjectId) &&
                Objects.equals(teacherName, course.teacherName) &&
                Objects.equals(dayOfWeek, course.dayOfWeek) &&
                Objects.equals(lessonNo, course.lessonNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, teacherName, dayOfWeek, lessonNo);
    }
}









