package es.jfpastor.app.service.export;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import es.jfpastor.app.models.PrintRecord;

@Component
public class ExportTXTFile implements IExport<PrintRecord> {
	Logger logger = LoggerFactory.getLogger(ExportTXTFile.class);
	
	ExportType EXPORT_TYPE = ExportType.TXT;

	@Override
	public ExportType getType() {
		return EXPORT_TYPE;
	}

	/**
	 * Export list to txt file
	 * 
	 * @list List of elements to export to file
	 * @args Parameters to export: 
	 * 	- args[0]: path to export 
	 * 	- args[1]: file name
	 */
	@Override
	public void export(List<? extends PrintRecord> list, String... args) throws Exception {
		if (args==null || args.length<2) {
			throw new Exception("Parametros incorrectos para exportar a fichero txt");
		}
		
		Path filePath = Paths.get(args[0]); //Folder
		if (!Files.exists(filePath)) {
			Files.createDirectories(filePath);
		}
		
		Path completePath = Paths.get(args[0]+ FileSystems.getDefault().getSeparator() + args[1] + ".txt"); // file name
		Files.createFile(completePath);
		//Header
		try {
			Files.writeString(completePath, 
					list.get(0).getHeader() + System.lineSeparator(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			logger.error("**** Se ha producido un error al generar el fichero txt: {} ****", e.getMessage());
			throw e;
		}
		
		//records
		list.stream().forEach(object -> {
			try {
				Files.writeString(completePath, object.getRecord() + System.lineSeparator(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				logger.error("**** Se ha producido un error al generar el fichero txt: {} ****", e.getMessage());
			}
		});
	}

}
