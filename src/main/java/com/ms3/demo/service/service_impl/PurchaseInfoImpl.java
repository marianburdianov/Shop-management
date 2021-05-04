package com.ms3.demo.service.service_impl;

import com.ms3.demo.model.dao.PurchaseInfoDao;
import com.ms3.demo.model.entities.PurchaseInfo;
import com.ms3.demo.service.service_decl.PurchaseInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseInfoImpl implements PurchaseInfoService {

    private final PurchaseInfoDao purchaseInfoDao;

    @Override
    public List<PurchaseInfo> getAllPurchaseInfo() {
        return purchaseInfoDao.findAll();
    }

    @Override
    public PurchaseInfo createPurchaseInfo(PurchaseInfo purchaseInfo) {
        return purchaseInfoDao.save(purchaseInfo);
    }

    @Override
    public PurchaseInfo getPurchaseInfoById(long purchaseInfoId) {
        return purchaseInfoDao.findByPurchaseInfoId(purchaseInfoId);
    }

    @Override
    public PurchaseInfo updatePurchaseInfoById(PurchaseInfo purchaseInfoForUpdate, long purchaseInfoId) {

        final PurchaseInfo purchaseInfo = purchaseInfoDao.findById(purchaseInfoId).orElseThrow(() -> new ApplicationContextException("PurchaseInfo not found"));

        purchaseInfo.setCost(purchaseInfoForUpdate.getCost());
        purchaseInfo.setAmount(purchaseInfoForUpdate.getAmount());
        purchaseInfo.setDate(purchaseInfoForUpdate.getDate());
        purchaseInfo.setEmployee(purchaseInfoForUpdate.getEmployee());
        purchaseInfo.setCustomer(purchaseInfoForUpdate.getCustomer());
        purchaseInfo.setProductSet(purchaseInfoForUpdate.getProductSet());

        return purchaseInfoDao.save(purchaseInfo);
    }

    @Override
    public void deletePurchaseInfoById(long purchaseInfoId) {
        purchaseInfoDao.deleteById(purchaseInfoId);
    }
}
