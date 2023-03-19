package es.jfpastor.app.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.jfpastor.app.TestData;

class SupplierTest {
	
	Supplier supplier;

	@BeforeEach
	void setUp() throws Exception {
		TestData data = new TestData();
		supplier = data.getSuppliers().get(0);
	}

	@Test
	void testGetHeader() {
		assertTrue("id, nombre, fechaalta".equalsIgnoreCase(supplier.getHeader()));
	}

	@Test
	void testGetRecord() {
		assertTrue("1, Coca-cola, 01/01/2023".equalsIgnoreCase(supplier.getRecord()));
	}

	@Test
	void testParseDate() {
		assertTrue("01/01/2023".equalsIgnoreCase(supplier.parseDate()));
	}

	@Test
	void testCanEqual() {
		assertTrue(supplier.getId().equals(1L));
		assertTrue("Coca-cola".equals(supplier.getName()));
		assertTrue(supplier.getRegistrationDate().equals(LocalDateTime.of(2023, 1, 1, 0, 0)));
		
		assertFalse(supplier.getId().equals(2L));
		assertFalse("Pepsi".equals(supplier.getName()));
		assertFalse(supplier.getRegistrationDate().equals(LocalDateTime.of(2023, 1, 2, 0, 0)));
		
	}

}
