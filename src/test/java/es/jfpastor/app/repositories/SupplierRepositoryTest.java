package es.jfpastor.app.repositories;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import es.jfpastor.app.controller.GenerateController;
import es.jfpastor.app.controller.IGenerateController;
import es.jfpastor.app.models.Customer;
import es.jfpastor.app.models.Supplier;
import es.jfpastor.app.service.ISupplierService;
import es.jfpastor.app.service.SupplierService;
import es.jfpastor.app.service.export.ExportCSVFile;
import es.jfpastor.app.service.export.ExportFactory;
import es.jfpastor.app.service.export.ExportService;
import es.jfpastor.app.service.export.ExportTXTFile;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class SupplierRepositoryTest {
	
	@TestConfiguration
    static class SupplierRepositoryTestContextConfiguration {
		@Bean
        public IGenerateController generateController() {
            return new GenerateController();
        }
		
		@Bean
        public ISupplierService supplierService() {
            return new SupplierService();
        }
		
        @Bean
        public ExportService exportService() {
            return new ExportService();
        }
        
        @Bean
        public ExportFactory exportFactory() {
        	return new ExportFactory(Arrays.asList(new ExportTXTFile(), new ExportCSVFile()));
        }
    }
	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SupplierRepository repository;

	@Test
	void testFindSupplierByCustomerssuppliersId() {
		// given
		Supplier suppl = new Supplier();
		//suppl.setId(50L);
		suppl.setName("Test supplier");
		suppl.setRegistrationDate(LocalDateTime.of(2022, 4, 21, 0, 0));
		
	    entityManager.persist(suppl);
		
		Customer customer = new Customer();
		customer.setName("Test customer");
		customer.setCustomerssuppliers(Arrays.asList(suppl));
		
		entityManager.persist(customer);
		
		entityManager.flush();

	    // when
	    Optional<List<Supplier>> found = repository.findSupplierByCustomerssuppliersId(customer.getId());

	    // then
	    assertFalse(found.get().isEmpty());
	    
	    assertTrue(found.get().get(0).getId().equals(suppl.getId()));
	}

}
