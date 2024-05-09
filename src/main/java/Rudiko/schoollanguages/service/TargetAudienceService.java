package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.model.TargetAudience;

import java.util.List;

public interface TargetAudienceService {
    List<TargetAudience> listProducts();

    TargetAudience getProductById(Long id);

    List<TargetAudience> getWhomList(String whom);

    void saveTargetAudience(TargetAudience product);

    void deleteProduct(Long id);
}
