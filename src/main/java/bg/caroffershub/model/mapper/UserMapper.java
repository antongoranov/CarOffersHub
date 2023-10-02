package bg.caroffershub.model.mapper;

import bg.caroffershub.model.dtos.UserRegisterDTO;
import bg.caroffershub.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //setting the field active, being present in the entity to true manually as it is missing from the DTO
    @Mapping(target = "active", constant = "true")
    UserEntity userRegisterDTOtoUserEntity(UserRegisterDTO userRegisterDTO);
}
