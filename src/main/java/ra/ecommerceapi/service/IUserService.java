package ra.ecommerceapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.ecommerceapi.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();

    Page<User> findAllPagination(Pageable pageable);

    List<User> findAllExceptAdmin();

    User findUserExceptAdminById(Long id);

    User save(User user);

    User save(User user,Long id);

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    void toggleStatus(Long id);

    List<User> findAllByFullName(String fullName);

}
