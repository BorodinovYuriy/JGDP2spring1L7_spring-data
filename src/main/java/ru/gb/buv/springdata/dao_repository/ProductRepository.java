
package ru.gb.buv.springdata.dao_repository;

import org.springframework.data.jpa.repository.*;
import ru.gb.buv.springdata.entity.Product;

import java.util.List;

/*@Repository//-не обязательная, но наглядненько*/
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostBetween(Long min, Long max);


}
