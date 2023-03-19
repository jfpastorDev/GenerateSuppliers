package es.jfpastor.app.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.management.AttributeNotFoundException;

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
import es.jfpastor.app.service.ISupplierService;
import es.jfpastor.app.service.export.ExportCSVFile;
import es.jfpastor.app.service.export.ExportFactory;
import es.jfpastor.app.service.export.ExportService;
import es.jfpastor.app.service.export.ExportTXTFile;

@ExtendWith(SpringExtension.class)
class GenerateControllerTest {
	
	@TestConfiguration
    static class ExportServiceTestContextConfiguration {
		@Bean
        public IGenerateController generateController() {
            return new GenerateController();
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

	@MockBean
	ISupplierService service;
	
	@Autowired
	IGenerateController controller;
	
	List<Supplier> suppliers;
	
	@BeforeEach
	void setUp() throws Exception {
		TestData data = new TestData();
		Customer customer = data.getCustomers().stream().filter(c -> c.getId() == 5L).findAny().get();
		suppliers = customer.getCustomerssuppliers();
	}
	
	@Test
	void testGenerateTXT() throws Exception {
		//given
		when(service.getSuppliersByCustomerId(5L))
	      .thenReturn(suppliers);
		
		//when
		String file = controller.generate(new String[] {"5"});
		
		//then
		Path testFilePath = Paths.get(GenerateController.PATH + FileSystems.getDefault().getSeparator() + file + ".txt");
		assertTrue(Files.exists(testFilePath));
	}

	@Test
	void testGenerateCSV() throws Exception {
		//given
		when(service.getSuppliersByCustomerId(5L))
	      .thenReturn(suppliers);
		
		//when
		String file = controller.generate(new String[] {"5", "CSV"});
		
		//then
		Path testFilePath = Paths.get(GenerateController.PATH + FileSystems.getDefault().getSeparator() + file + ".csv");
		assertTrue(Files.exists(testFilePath));
	}
	
	@Test
	void testGenerateEmpty() throws Exception {
		//given
		when(service.getSuppliersByCustomerId(5L))
	      .thenReturn(suppliers);
		
		//then
		assertThrows(AttributeNotFoundException.class, () -> controller.generate(new String[] {}));
	}
	
	@Test
	void testGenerateNumberException() throws Exception {
		//given
		when(service.getSuppliersByCustomerId(5L))
	      .thenReturn(suppliers);
		
		//then
		assertThrows(NumberFormatException.class, () -> controller.generate(new String[] {"TEST"}));
	}
}
