package com.ms3.demo.controller;

import com.ms3.demo.model.entities.PurchaseInfo;
import com.ms3.demo.service.service_decl.PurchaseInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/purchaseInfo")
public class PurchaseInfoController {

    private final PurchaseInfoService purchaseInfoService;

    @GetMapping("/")
    public List<PurchaseInfo> getAllPurchaseInfo() {

        return purchaseInfoService.getAllPurchaseInfo();
    }

    @GetMapping("/{purchaseInfoId}")
    public PurchaseInfo getPurchaseInfoById(@PathVariable Long purchaseInfoId) {

        return purchaseInfoService.getPurchaseInfoById(purchaseInfoId);
    }

    @PostMapping("/")
    public PurchaseInfo addPurchaseInfo(@RequestBody PurchaseInfo purchaseInfo) {

        purchaseInfoService.createPurchaseInfo(purchaseInfo);

        return purchaseInfo;
    }

    @PutMapping(value = "/{purchaseInfoId}")
    public PurchaseInfo updatePurchaseInfo(@RequestBody PurchaseInfo purchaseInfo, @PathVariable Long purchaseInfoId) {

        purchaseInfoService.updatePurchaseInfoById(purchaseInfo, purchaseInfoId);
        return purchaseInfo;
    }

    @DeleteMapping("/{purchaseInfoId}")
    public void deletePurchaseInfoById(@PathVariable Long purchaseInfoId) {

        purchaseInfoService.deletePurchaseInfoById(purchaseInfoId);
    }
}
