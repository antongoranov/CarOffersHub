package bg.caroffershub.repository;

import bg.caroffershub.model.entity.UserRoleEntity;
import bg.caroffershub.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByUserRole(UserRoleEnum userRole);
}
