package Rudiko.schoollanguages.controller;

import Rudiko.schoollanguages.model.SupportApplication;
import Rudiko.schoollanguages.repository.SupportApplicationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/support")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SupportApplicationsController {
    private final SupportApplicationsRepository supportApplicationsRepository;

    @GetMapping
    public List<SupportApplication> getAllSupportApplications(){
        return supportApplicationsRepository.findAll();
    }

    @PostMapping("/save_supportApplication")
    public SupportApplication saveSupportApplications(@RequestBody SupportApplication supportApplication){
        return supportApplicationsRepository.save(supportApplication);
    }
}
