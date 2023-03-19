package es.jfpastor.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jfpastor.app.models.Supplier;
import es.jfpastor.app.repositories.SupplierRepository;

@Service
public class SupplierService implements ISupplierService {

	 @Autowired
	 private SupplierRepository supplierRepository;

	@Override
	public List<Supplier> getSuppliersByCustomerId(Long customerId) {
		return supplierRepository.findSupplierByCustomerssuppliersId(customerId).orElse(null);
	}
	 
}
