package com.ms3.demo.service.service_impl;

import com.ms3.demo.model.dao.PurchaseInfoDao;
import com.ms3.demo.model.entities.PurchaseInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PurchaseInfoImplTest {

    private static final long ID = 1L;
    private PurchaseInfo firstPurchaseInfo;
    private PurchaseInfo secondPurchaseInfo;

    @InjectMocks
    private PurchaseInfoImpl purchaseInfoService;

    @Mock
    private PurchaseInfoDao purchaseInfoDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        purchaseInfoService = new PurchaseInfoImpl(purchaseInfoDao);

        firstPurchaseInfo = new PurchaseInfo();
        firstPurchaseInfo.setPurchaseInfoId(1L);
        firstPurchaseInfo.setCost(300.0);
        firstPurchaseInfo.setAmount(9);
        firstPurchaseInfo.setDate(LocalDate.of(2020, 03, 21));

        secondPurchaseInfo = new PurchaseInfo();
        secondPurchaseInfo.setPurchaseInfoId(2L);
        secondPurchaseInfo.setCost(250.0);
        secondPurchaseInfo.setAmount(12);
        secondPurchaseInfo.setDate(LocalDate.of(2019, 07, 02));
    }

    @Test
    void getAllPurchaseInfo() {
        List<PurchaseInfo> purchaseInfoList = new ArrayList<>();
        purchaseInfoList.add(firstPurchaseInfo);
        purchaseInfoList.add(secondPurchaseInfo);

        when(purchaseInfoDao.findAll()).thenReturn(purchaseInfoList);

        List<PurchaseInfo> list = purchaseInfoService.getAllPurchaseInfo();

        assertEquals(2, list.size());

        verify(purchaseInfoDao, times(1)).findAll();
    }

    @Test
    void createPurchaseInfo() {

        when(purchaseInfoDao.save(firstPurchaseInfo)).thenReturn(firstPurchaseInfo);

        purchaseInfoService.createPurchaseInfo(firstPurchaseInfo);

        verify(purchaseInfoDao, times(1)).save(firstPurchaseInfo);
    }

    @Test
    void getPurchaseInfoById() {

        when(purchaseInfoDao.findByPurchaseInfoId(ID)).thenReturn(firstPurchaseInfo);

        PurchaseInfo purchaseInfoForVerify = purchaseInfoService.getPurchaseInfoById(ID);

        assertEquals(firstPurchaseInfo.getPurchaseInfoId(), purchaseInfoForVerify.getPurchaseInfoId());

        verify(purchaseInfoDao, times(1)).findByPurchaseInfoId(ID);
    }

    @Test
    void updatePurchaseInfoById() {

        when(purchaseInfoDao.findById(ID)).thenReturn(Optional.of(firstPurchaseInfo));
        when(purchaseInfoDao.save(firstPurchaseInfo)).thenReturn(firstPurchaseInfo);

        PurchaseInfo updatedPurchaseInfo = purchaseInfoService.updatePurchaseInfoById(secondPurchaseInfo, ID);

        assertNotNull(updatedPurchaseInfo);
        assertEquals(firstPurchaseInfo.getPurchaseInfoId(), updatedPurchaseInfo.getPurchaseInfoId());
        assertEquals(firstPurchaseInfo.getAmount(), updatedPurchaseInfo.getAmount());
        assertEquals(firstPurchaseInfo.getCost(), updatedPurchaseInfo.getCost());
        assertEquals(firstPurchaseInfo.getDate(), updatedPurchaseInfo.getDate());

        verify(purchaseInfoDao, times(1)).save(updatedPurchaseInfo);

    }

    @Test
    void deletePurchaseInfoById() {

        doNothing().when(purchaseInfoDao).deleteById(anyLong());

        purchaseInfoService.deletePurchaseInfoById(ID);

        verify(purchaseInfoDao, times(1)).deleteById(ID);
    }
}