package bg.caroffershub.model.mapper;

import bg.caroffershub.model.dtos.AddOfferDTO;
import bg.caroffershub.model.dtos.OfferDetailsDTO;
import bg.caroffershub.model.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferEntity addOfferDTOtoOfferEntity(AddOfferDTO addOfferDTO);

    @Mapping(source = "model.name", target = "model")
    @Mapping(source = "model.brand.name", target = "brand")
    @Mapping(source = "seller.firstName", target = "sellerFirstName")
    @Mapping(source = "seller.lastName", target = "sellerLastName")
    OfferDetailsDTO offerEntityToOfferDetailsDto(OfferEntity offerEntity);
}
