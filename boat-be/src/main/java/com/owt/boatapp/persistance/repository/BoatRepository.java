package com.owt.boatapp.persistance.repository;

import com.owt.boatapp.persistance.dao.BoatDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatRepository extends JpaRepository<BoatDao, Long> {
}