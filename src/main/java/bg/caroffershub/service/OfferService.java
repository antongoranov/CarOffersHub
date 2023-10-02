package bg.caroffershub.service;

import bg.caroffershub.model.dtos.AddOfferDTO;
import bg.caroffershub.model.dtos.DeleteOfferDTO;
import bg.caroffershub.model.dtos.OfferDetailsDTO;
import bg.caroffershub.model.dtos.SearchOfferDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    void addOffer(AddOfferDTO addOfferDTO, UserDetails userDetails);

    Page<OfferDetailsDTO> getAllOffers(Pageable pageable);

    List<OfferDetailsDTO> searchOffer(SearchOfferDTO searchOfferDTO);

    void deleteOfferById(DeleteOfferDTO deleteOfferDTO);

    OfferDetailsDTO findOfferById(UUID id);
}
