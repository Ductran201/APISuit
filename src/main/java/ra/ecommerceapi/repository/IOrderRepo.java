package ra.ecommerceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.ecommerceapi.model.constant.OrderStatus;
import ra.ecommerceapi.model.entity.Orders;

public interface IOrderRepo extends JpaRepository<Orders,Long> {
    Page<Orders> findAllByCodeContains(String code, Pageable pageable);

    Page<Orders> findAllByStatus(OrderStatus orderStatus,Pageable pageable);

}
