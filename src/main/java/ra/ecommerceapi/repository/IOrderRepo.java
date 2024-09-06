package ra.ecommerceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.ecommerceapi.model.constant.OrderStatus;
import ra.ecommerceapi.model.entity.Orders;
import ra.ecommerceapi.model.entity.User;

import java.util.Optional;

public interface IOrderRepo extends JpaRepository<Orders, Long> {
    Page<Orders> findAllByCodeContains(String code, Pageable pageable);

    Page<Orders> findAllByUserAndCodeContains(User user, String code, Pageable pageable);

    Page<Orders> findAllByStatus(OrderStatus orderStatus, Pageable pageable);

    Page<Orders> findAllByUserAndStatus(User user, OrderStatus orderStatus, Pageable pageable);

    Optional<Orders> findByUserAndCode(User user, String code);

    Optional<Orders> findByUserAndId(User user, Long id);



}
