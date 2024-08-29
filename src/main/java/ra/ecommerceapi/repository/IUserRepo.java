package ra.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ra.ecommerceapi.model.entity.User;

import java.util.List;
import java.util.Optional;

@Transactional
public interface IUserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT u.* " +
            "FROM user u " +
            "WHERE u.id = :userId " +
            "AND u.id NOT IN ( " +
            "    SELECT ur.user_id " +
            "    FROM user_role ur " +
            "             JOIN role r ON ur.role_id = r.id " +
            "    WHERE r.roleName = 'ROLE_ADMIN' " +
            ");", nativeQuery = true)
    Optional<User> findUserExceptAdminById(@Param("userId") Long userId);

    @Query(value = "SELECT u.*\n" +
            "FROM user u\n" +
            "WHERE u.id NOT IN (\n" +
            "    SELECT ur.user_id\n" +
            "    FROM user_role ur\n" +
            "             JOIN role r ON ur.role_id = r.id\n" +
            "    WHERE r.roleName = 'ROLE_ADMIN'\n" +
            ");", nativeQuery = true)
    List<User> findAllExceptAdmin();

//    @Query("select u from User u where  ")
//    List<User> findAllExceptAdmin();

    @Query("update User u set u.status = (not u.status) where u.id = :id")
    @Modifying
    void toggleStatus(@Param("id") Long id);

    @Query(value = "SELECT u.* " +
            "FROM user u " +
            "WHERE u.fullName LIKE %:fullName% " +
            "AND u.id NOT IN ( " +
            "    SELECT ur.user_id " +
            "    FROM user_role ur " +
            "             JOIN role r ON ur.role_id = r.id " +
            "    WHERE r.roleName = 'ROLE_ADMIN' " +
            ");", nativeQuery = true)
    List<User> findAllByFullNameContaining(String fullName);



}
