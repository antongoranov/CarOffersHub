package bg.caroffershub.service.impl;

import bg.caroffershub.model.dtos.AddOfferDTO;
import bg.caroffershub.model.dtos.DeleteOfferDTO;
import bg.caroffershub.model.dtos.OfferDetailsDTO;
import bg.caroffershub.model.dtos.SearchOfferDTO;
import bg.caroffershub.model.entity.ModelEntity;
import bg.caroffershub.model.entity.OfferEntity;
import bg.caroffershub.repository.ModelRepository;
import bg.caroffershub.repository.OfferRepository;
import bg.caroffershub.model.entity.UserEntity;
import bg.caroffershub.model.mapper.OfferMapper;
import bg.caroffershub.repository.OfferSpecification;
import bg.caroffershub.repository.UserRepository;
import bg.caroffershub.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;

    private final OfferMapper offerMapper;


    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository,
                            ModelRepository modelRepository,
                            UserRepository userRepository,

                            OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;

        this.offerMapper = offerMapper;

    }

    @Override
    public void addOffer(AddOfferDTO addOfferDTO, UserDetails userDetails) {
        OfferEntity newOffer = this.offerMapper.addOfferDTOtoOfferEntity(addOfferDTO);

        UserEntity seller = this.userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow();

        ModelEntity model = this.modelRepository.findById(addOfferDTO.getModelId())
                .orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);


        this.offerRepository.save(newOffer);
    }


    @Override
    public Page<OfferDetailsDTO> getAllOffers(Pageable pageable) {
        return offerRepository
                .findAll(pageable)
                .map(this.offerMapper::offerEntityToOfferDetailsDto);
    }

    @Override
    public List<OfferDetailsDTO> searchOffer(SearchOfferDTO searchOfferDTO) {
        return this.offerRepository.findAll(new OfferSpecification(searchOfferDTO))
                .stream()
                .map(offer -> this.offerMapper.offerEntityToOfferDetailsDto(offer))
                .toList();
    }

    @Override
    public void deleteOfferById(DeleteOfferDTO deleteOfferDTO) {
        this.offerRepository.deleteById(UUID.fromString(deleteOfferDTO.getUuid()));
    }

    @Override
    public OfferDetailsDTO findOfferById(UUID id) {
        return this.offerRepository.findById(id)
                .map(this.offerMapper::offerEntityToOfferDetailsDto)
                .orElseThrow();
    }


}
