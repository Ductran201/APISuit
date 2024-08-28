package ra.ecommerceapi.service;

import ra.ecommerceapi.model.constant.RoleName;
import ra.ecommerceapi.model.entity.Role;

import java.util.List;

public interface IRoleService {
    Role findByRoleName(RoleName roleName);

    List<Role> findAll();
}
