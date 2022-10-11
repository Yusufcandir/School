package com.example.school.model;

import com.example.school.enumeration.*;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
@Data
@Entity
public class  Course{
ArchitectureCourse architectureCourse;
CivilEngineering civilEngineering;
ComputerScienceCourse computerScienceCourse;
EnvironmentalEngineering environmentalEngineering;
Physics physics;

    @Id
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Student> student ;


}
