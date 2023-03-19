package es.jfpastor.app.service.export;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import es.jfpastor.app.TestData;
import es.jfpastor.app.controller.GenerateController;
import es.jfpastor.app.controller.IGenerateController;
import es.jfpastor.app.models.Supplier;
import es.jfpastor.app.service.ISupplierService;
import es.jfpastor.app.service.SupplierService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ExportServiceTest {

	List<Supplier> suppliers;
	
	@Autowired
	ExportService service;
	
	@TestConfiguration
    static class ExportServiceTestContextConfiguration {
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
	
	@BeforeEach
	void setUp() throws Exception {
		TestData data = new TestData();
		
		suppliers = data.getSuppliers();
	}

	@Test
	void testExportFile() throws Exception {
		Path path = Paths.get("junittest");
		if (Files.exists(path)) {
			deleteDirectory(path);
		}
		
		service.exportFile(suppliers, ExportType.TXT, "junittest", "test");
		
		Path testFilePath = Paths.get("junittest" + FileSystems.getDefault().getSeparator() + "test.txt");
		assertTrue(Files.exists(testFilePath));

		service.exportFile(suppliers, ExportType.CSV, "junittest", "test");
		testFilePath = Paths.get("junittest" + FileSystems.getDefault().getSeparator() + "test.csv");
		assertTrue(Files.exists(testFilePath));
		
		if (Files.exists(path)) {
			deleteDirectory(path);
		}
	}
	
	private void deleteDirectory(Path path) throws IOException {
		Files.walk(path)
			.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
	}

}
