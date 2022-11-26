package com.example.springboot.Repository;

import com.example.springboot.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity,Long> {

    @Query(value = "SELECT * FROM CUSTOMER  WHERE NATIONAL_CODE = :NationalCode",nativeQuery = true)
    List<CustomerEntity> findByNationalId(@Param("NationalCode") String NationalCode);
}
