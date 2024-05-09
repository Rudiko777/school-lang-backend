package Rudiko.schoollanguages.service.impl;

import Rudiko.schoollanguages.model.TargetAudience;
import Rudiko.schoollanguages.repository.TargetAudienceRepository;
import Rudiko.schoollanguages.service.TargetAudienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TargetAudienceServiceImpl implements TargetAudienceService {
    private final TargetAudienceRepository targetAudienceRepository;

    @Override
    public List<TargetAudience> listProducts() {
        List<TargetAudience> targetAudienceList;
        targetAudienceList = targetAudienceRepository.findAll();
        return targetAudienceList;
    }

    @Override
    public TargetAudience getProductById(Long id) {
        return targetAudienceRepository.findById(id).orElse(null);
    }

    @Override
    public List<TargetAudience> getWhomList(String whom) {
        return targetAudienceRepository.findByWhom(whom);
    }

    @Override
    public void saveTargetAudience(TargetAudience product) {
        targetAudienceRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        targetAudienceRepository.deleteById(id);
    }
}
