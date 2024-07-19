package MVC_OnJC.MVC_practice_3.repository;

import MVC_OnJC.MVC_practice_3.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity getReferenceByEmail(String email);

}
