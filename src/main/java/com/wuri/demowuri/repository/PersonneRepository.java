package com.wuri.demowuri.repository;

import com.wuri.demowuri.model.Personnne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personnne, Integer> {


}
