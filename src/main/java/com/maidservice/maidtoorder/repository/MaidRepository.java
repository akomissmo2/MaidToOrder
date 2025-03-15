package com.maidservice.maidtoorder.repository;

import com.maidservice.maidtoorder.entity.Maid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaidRepository extends JpaRepository<Maid, Long> {
}