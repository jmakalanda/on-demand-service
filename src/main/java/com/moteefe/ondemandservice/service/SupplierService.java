package com.moteefe.ondemandservice.service;

import com.moteefe.ondemandservice.model.Product;
import com.moteefe.ondemandservice.model.Supplier;
import com.moteefe.ondemandservice.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    private SupplierRepository supplierRepository;

    public SupplierService() {
    }

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public void saveAll(List<Supplier> suppliers){
        supplierRepository.saveAll(suppliers);
    }

}
