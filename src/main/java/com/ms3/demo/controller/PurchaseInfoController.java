package com.ms3.demo.controller;

import com.ms3.demo.model.entities.PurchaseInfo;
import com.ms3.demo.service.service_decl.PurchaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/purchaseInfo")
@Api(value = "purchaseInfo", description = "CRUD Operations for PurchaseInfo", tags = "PURCHASEINFO")
public class PurchaseInfoController {

    private final PurchaseInfoService purchaseInfoService;

    @GetMapping
    @ApiOperation(value = "GET ALL PURCHASEINFOS", notes = "\n" + "This operation gets all purchaseInfos")
    public List<PurchaseInfo> getAllPurchaseInfo() {

        return purchaseInfoService.getAllPurchaseInfo();
    }

    @GetMapping("/{purchaseInfoId}")
    @ApiOperation(value = "GET PURCHASEINFO BY ID", notes = "\n" + "This operation get purchaseInfo by id")
    public PurchaseInfo getPurchaseInfoById(@PathVariable Long purchaseInfoId) {

        return purchaseInfoService.getPurchaseInfoById(purchaseInfoId);
    }

    @PostMapping
    @ApiOperation(value = "CREATE PURCHASEINFO", notes = "\n" + "This operation creates a purchaseInfo")
    public PurchaseInfo addPurchaseInfo(@RequestBody PurchaseInfo purchaseInfo) {

        purchaseInfoService.createPurchaseInfo(purchaseInfo);

        return purchaseInfo;
    }

    @PutMapping(value = "/{purchaseInfoId}")
    @ApiOperation(value = "UPDATE PURCHASEINFO", notes = "\n" + "This operation updates a existing purchaseInfo")

    public PurchaseInfo updatePurchaseInfo(@RequestBody PurchaseInfo purchaseInfo, @PathVariable Long purchaseInfoId) {

        purchaseInfoService.updatePurchaseInfoById(purchaseInfo, purchaseInfoId);
        return purchaseInfo;
    }

    @DeleteMapping("/{purchaseInfoId}")
    @ApiOperation(value = "DELETE PURCHASEINFO BY ID", notes = "\n" + "This operation delete purchaseInfo by id")
    public void deletePurchaseInfoById(@PathVariable Long purchaseInfoId) {

        purchaseInfoService.deletePurchaseInfoById(purchaseInfoId);
    }
}
