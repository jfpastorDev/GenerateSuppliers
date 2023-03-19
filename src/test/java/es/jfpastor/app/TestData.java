package es.jfpastor.app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.jfpastor.app.models.Customer;
import es.jfpastor.app.models.Supplier;
import lombok.Data;

/**
 * Testing data
 */
@Data
public class TestData {
	
	private List<Supplier> suppliers = new ArrayList<>();
	private List<Customer> customers = new ArrayList<>();
	
	public TestData() {
		Supplier s1 = new Supplier(1L, "Coca-cola", LocalDateTime.of(2023, 1, 1, 0, 0), null);
		Supplier s2 = new Supplier(2L, "Pepsi", LocalDateTime.of(2023, 1, 2, 0, 0), null);
		Supplier s3 = new Supplier(3L, "Redbull", LocalDateTime.of(2023, 1, 3, 0, 0), null);
		
		setSuppliers(Arrays.asList(s1, s2, s3));
		
		Customer c1 = new Customer(1L, "Cliente 1", null);
		Customer c2 = new Customer(2L, "Cliente 2", null);
		Customer c3 = new Customer(3L, "Cliente 3", null);
		Customer c4 = new Customer(4L, "Cliente 4", null);
		Customer c5 = new Customer(5L, "Cliente 5", null);
		Customer c6 = new Customer(6L, "Cliente 6", null);
		
		customers = Arrays.asList(c1, c2, c3, c4, c5, c6);

		/*
		 	INSERT INTO clientesproveedores (cliente_id, proveedor_id) VALUES (5, 1);
			INSERT INTO clientesproveedores (cliente_id, proveedor_id) VALUES (5, 2);
		 */
		c5.setCustomerssuppliers(Arrays.asList(s1, s2));
		s1.setCustomerssuppliers(Arrays.asList(c5));
		s2.setCustomerssuppliers(Arrays.asList(c5));

		/*
			INSERT INTO clientesproveedores (cliente_id, proveedor_id) VALUES (6, 3);
		 */
		c6.setCustomerssuppliers(Arrays.asList(s3));
		s3.setCustomerssuppliers(Arrays.asList(c6));
	}

}
