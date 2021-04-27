package com.ms3.demo.model.dao;

import com.ms3.demo.model.entities.PurchaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseInfoDao extends JpaRepository<PurchaseInfo, Long> {

    PurchaseInfo findByPurchaseInfoId(Long id);

    void deleteById(Long id);
}
