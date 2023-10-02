package bg.caroffershub.service;

import bg.caroffershub.model.dtos.UserRegisterDTO;

public interface UserService {
    void registerAndLogin(UserRegisterDTO userRegisterDTO);
}
