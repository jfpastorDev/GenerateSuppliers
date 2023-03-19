package es.jfpastor.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.jfpastor.app.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	public Optional<List<Supplier>> findSupplierByCustomerssuppliersId(Long customerId);
}
