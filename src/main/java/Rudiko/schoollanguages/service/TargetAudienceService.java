package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.model.TargetAudience;
import Rudiko.schoollanguages.repository.TargetAudienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TargetAudienceService {
    private final TargetAudienceRepository targetAudienceRepository;


    public List<TargetAudience> listProducts() {
        List<TargetAudience> targetAudienceList;
        targetAudienceList = targetAudienceRepository.findAll();
        return targetAudienceList;
    }


    public TargetAudience getProductById(Long id) {
        return targetAudienceRepository.findById(id).orElse(null);
    }


    public List<TargetAudience> getWhomList(String whom) {
        return targetAudienceRepository.findByWhom(whom);
    }


    public void saveTargetAudience(TargetAudience product) {
        targetAudienceRepository.save(product);
    }

    public void deleteProduct(Long id) {
        targetAudienceRepository.deleteById(id);
    }
}
