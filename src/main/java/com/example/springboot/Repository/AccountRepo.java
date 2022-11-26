package com.example.springboot.Repository;

import com.example.springboot.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<AccountEntity,Long> {

    @Query(value = "SELECT * FROM ACCOUNT ",nativeQuery = true)
    List<AccountEntity> findByNationalId();




}
