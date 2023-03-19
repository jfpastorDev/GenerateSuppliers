package es.jfpastor.app.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import es.jfpastor.app.TestData;
import es.jfpastor.app.models.Customer;
import es.jfpastor.app.models.Supplier;
import es.jfpastor.app.repositories.SupplierRepository;

@ExtendWith(SpringExtension.class)
class SupplierServiceTest {

	@MockBean
	SupplierRepository repository;
	
	@Autowired
	ISupplierService service;
	
	@TestConfiguration
    static class SupplierServiceTestContextConfiguration {
        @Bean
        public ISupplierService supplierService() {
            return new SupplierService();
        }
    }
	
	List<Supplier> suppliers;
	
	@BeforeEach
	void setUp() throws Exception {
		TestData data = new TestData();
		Customer customer = data.getCustomers().stream().filter(c -> c.getId() == 5L).findAny().get();
		suppliers = customer.getCustomerssuppliers();
	}

	@Test
	void testGetSuppliersByCustomerId() {
		//given
		when(repository.findSupplierByCustomerssuppliersId(5L))
	      .thenReturn(Optional.of(suppliers));
		
		//when
		List<Supplier> ls = service.getSuppliersByCustomerId(5L);
		
		//then
		for (Supplier s: ls) {
			if (s.getId().equals(1L)) {
				assertTrue("1, Coca-cola, 01/01/2023".equals(s.getRecord()));
				assertFalse("2, Pepsi, 02/01/2023".equals(s.getRecord()));
			} else if (s.getId().equals(2L)) {
				assertTrue("2, Pepsi, 02/01/2023".equals(s.getRecord()));
				assertFalse("1, Coca-cola, 01/01/2023".equals(s.getRecord()));
			}
		}

	}

	@Test
	void testGetSuppliersByCustomerIdEmpty() {
		//given
		when(repository.findSupplierByCustomerssuppliersId(1L))
			.thenReturn(Optional.ofNullable(null));
		
		//when
		List<Supplier> ls = service.getSuppliersByCustomerId(1L);
		
		//then
		assertTrue(ls == null);
	}
}
