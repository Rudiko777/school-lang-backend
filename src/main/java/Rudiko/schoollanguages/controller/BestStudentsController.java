package Rudiko.schoollanguages.controller;

import Rudiko.schoollanguages.dtos.BestStudentsDto;
import Rudiko.schoollanguages.model.BestStudents;
import Rudiko.schoollanguages.service.impl.BestStudentsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bestStudents")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BestStudentsController {
    private final BestStudentsServiceImpl bestStudentsService;

    @GetMapping
    private List<BestStudents> getAllBestStudents(){
        return bestStudentsService.getAllStudents();
    }

    @GetMapping("/{fullName}")
    private BestStudents getAllBestStudents(@PathVariable String fullName){
        return bestStudentsService.getByFullName(fullName);
    }

    @PostMapping("/{action}")
    public void updateBestStudents(@PathVariable String action, @RequestBody BestStudentsDto bestStudents) {
        bestStudentsService.saveOrUpdateBestStudent(bestStudents, action);
    }


}
