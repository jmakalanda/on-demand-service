package com.moteefe.ondemandservice.repository;

import com.moteefe.ondemandservice.model.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier,Long> {

}
