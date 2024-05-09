package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.dtos.BestStudentsDto;
import Rudiko.schoollanguages.model.BestStudents;

import java.util.List;

public interface BestStudentsService {

    void saveOrUpdateBestStudent(BestStudentsDto student, String action);

    List<BestStudents> getAllStudents();

    BestStudents getByFullName(String fullName);
}
