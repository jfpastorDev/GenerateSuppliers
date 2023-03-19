package es.jfpastor.app.service;

import java.util.List;

import es.jfpastor.app.models.Supplier;

public interface ISupplierService {
	
	public List<Supplier> getSuppliersByCustomerId(Long customerId);

}
