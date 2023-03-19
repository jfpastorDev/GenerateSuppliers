package es.jfpastor.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.management.AttributeNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.jfpastor.app.models.Supplier;
import es.jfpastor.app.service.ISupplierService;
import es.jfpastor.app.service.export.ExportService;
import es.jfpastor.app.service.export.ExportType;

@Controller
public class GenerateController implements IGenerateController {
	Logger logger = LoggerFactory.getLogger(GenerateController.class);
	
	static final String PATH = "exportedFiles";
	final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
	
	@Autowired
	private ISupplierService supplierService;
	
	@Autowired
	private ExportService exportService;
	
	@Override
	public String generate(String... args) throws Exception {
		//Arrays.stream(args).forEach(System.out::println);
		if (args == null || args.length<=0) {
			printMessage("Debe indicar un código de cliente", new PrintError());
			throw new AttributeNotFoundException("Debe indicar un código de cliente");
		}
		
		String strCustomerId = args[0];
		try {
			Long customerId = Long.parseLong(strCustomerId);

			List<Supplier> list = supplierService.getSuppliersByCustomerId(customerId);
			
			if (list==null || list.isEmpty()) {
				printMessage("El cliente no tiene proveedores asignados", new PrintInfo());
				return null;
			}
			
			LocalDateTime now = LocalDateTime.now();
			String fileName = now.format(CUSTOM_FORMATTER) + "_proveedores_"+ customerId;
			
			//If there is a second argument with the type of file to export, it is selected
			//Only types: TXT, CSV (for now)
			String exportType = "TXT";
			if (args.length>1 && args[1]!=null) {
				switch (args[1]) {
				case "CSV":
					exportType = "CSV";
					break;
				default:
					break;
				}
			}

			exportService.exportFile(list, ExportType.valueOf(exportType), PATH, fileName);

			String[] arr = new String[] {fileName, strCustomerId};
			printMessage(String.format("Se ha generado el fichero %s con los proveedores del cliente %s." + exportType, (Object[]) arr), new PrintInfo());
			
			return fileName;
		} catch (NumberFormatException e) {
			printMessage("El código del cliente debe ser numérico", new PrintError());
			throw new NumberFormatException("El código del cliente debe ser numérico");
		} catch (Exception e) {
			printMessage(String.format("Se ha producido un error inesperado: %s", e.getMessage()), new PrintError());
		}

		return null;
	}
	
	private void printMessage (String message, Command func) {
		func.execute(message);
	}
	
	interface Command {
        public void execute(String message);
    }
	
	class PrintError implements Command {
		@Override
        public void execute(String message) {
			logger.error("*****************************************************************");
			logger.error("**** " + message + " ****");
			logger.error("*****************************************************************");
        }    
    }
	
	class PrintInfo implements Command {
		@Override
		public void execute(String message) {
			logger.info("*****************************************************************");
			logger.info("**** " + message + " ****");
			logger.info("*****************************************************************");
        }    
    }


}
