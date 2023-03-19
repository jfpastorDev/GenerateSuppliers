package es.jfpastor.app.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.jfpastor.app.TestData;

class CustomerTest {
	Customer customer;

	@BeforeEach
	void setUp() throws Exception {
		TestData data = new TestData();
		customer = data.getCustomers().get(0);
	}

	@Test
	void testGetHeader() {
		assertTrue("id, nombre".equalsIgnoreCase(customer.getHeader()));
	}

	@Test
	void testGetRecord() {
		assertTrue("1, Cliente 1".equalsIgnoreCase(customer.getRecord()));
	}

	@Test
	void testCanEqual() {
		assertTrue(customer.getId().equals(1L));
		assertTrue("Cliente 1".equals(customer.getName()));
		
		assertFalse(customer.getId().equals(2L));
		assertFalse("Cliente 2".equals(customer.getName()));
	}

}
