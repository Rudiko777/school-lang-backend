package Rudiko.schoollanguages.service.impl;

import Rudiko.schoollanguages.dtos.BestStudentsDto;
import Rudiko.schoollanguages.model.BestStudents;
import Rudiko.schoollanguages.model.LanguageCourse;
import Rudiko.schoollanguages.model.Module;
import Rudiko.schoollanguages.repository.BestStudentsRepository;
import Rudiko.schoollanguages.repository.LanguageCourseRepository;
import Rudiko.schoollanguages.service.BestStudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BestStudentsServiceImpl implements BestStudentsService {
    private final BestStudentsRepository bestStudentsRepository;
    private final LanguageCourseRepository languageCourseRepository;

    @Override
    public void saveOrUpdateBestStudent(BestStudentsDto student, String action) {
        BestStudents existingStudent = bestStudentsRepository.findByFullName(student.getFullName());
        Optional<LanguageCourse> languageCourseOptional = languageCourseRepository.findById(student.getCourseId());
        LanguageCourse languageCourse = languageCourseOptional.get();

        Optional<Module> moduleOptional = languageCourse.getModules().stream()
                .filter(module -> module.getId().equals(student.getIdActiveModule()))
                .findFirst();

        Module module = moduleOptional.get();

        if (existingStudent != null) {
            List<Module> updatedModules = existingStudent.getActiveModules();
            if (Objects.equals(action, "plus")){
                updatedModules.add(module);
                existingStudent.setScore(existingStudent.getScore() + student.getScore());
            } else if (Objects.equals(action, "minus")) {
                updatedModules.remove(module);
                existingStudent.setScore(existingStudent.getScore() - student.getScore());
            }
            if (existingStudent.getScore() == 0){
                bestStudentsRepository.delete(existingStudent);
                return;
            }
            existingStudent.setActiveModules(updatedModules);
            bestStudentsRepository.save(existingStudent);
        } else {
            List<Module> updatedModules = new ArrayList<>();
            updatedModules.add(module);
            BestStudents bestStudentsNew = new BestStudents(
                student.getFullName(),
                student.getScore(),
                updatedModules
            );
            bestStudentsRepository.save(bestStudentsNew);
        }
    }

    @Override
    public List<BestStudents> getAllStudents(){
        return bestStudentsRepository.findAll();
    }

    @Override
    public BestStudents getByFullName(String fullName){
        return bestStudentsRepository.findByFullName(fullName);
    }
}
