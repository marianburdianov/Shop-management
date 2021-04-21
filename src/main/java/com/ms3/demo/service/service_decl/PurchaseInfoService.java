package com.ms3.demo.service.service_decl;

import com.ms3.demo.model.entities.PurchaseInfo;

import java.util.List;

public interface PurchaseInfoService {

    List<PurchaseInfo> getAllPurchaseInfo();

    PurchaseInfo getPurchaseInfoById(long purchaseInfoId);

    PurchaseInfo createPurchaseInfo(PurchaseInfo purchaseInfo);

    void updatePurchaseInfoById(PurchaseInfo purchaseInfo, long purchaseInfoId);

    void deletePurchaseInfoById(long purchaseInfoId);

}

