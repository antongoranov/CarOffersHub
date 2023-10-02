package bg.caroffershub.service.impl;

import bg.caroffershub.model.dtos.BrandDTO;
import bg.caroffershub.model.dtos.ModelDTO;
import bg.caroffershub.model.entity.ModelEntity;
import bg.caroffershub.repository.BrandRepository;
import bg.caroffershub.service.BrandService;
import bg.caroffershub.model.entity.BrandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandDTO> getAllBrands() {

        return this.brandRepository
                .findAll()
                .stream()
                .map(this::mapBrand)
                .collect(Collectors.toList());
    }

    private BrandDTO mapBrand(BrandEntity brandEntity) {
        List<ModelDTO> models = brandEntity
                .getModels()
                .stream()
                .map(this::mapModel)
                .toList();

        return new BrandDTO()
                .setModels(models)
                .setName(brandEntity.getName());
    }

    private ModelDTO mapModel(ModelEntity modelEntity) {
        return new ModelDTO()
                .setId(modelEntity.getId())
                .setName(modelEntity.getName());
    }
}
