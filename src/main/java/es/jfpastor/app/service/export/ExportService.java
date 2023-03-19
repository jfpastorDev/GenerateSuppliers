package es.jfpastor.app.service.export;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jfpastor.app.models.PrintRecord;

@Service
public class ExportService {

	@Autowired
	private ExportFactory exportFactory;
	
	public void exportFile(List<? extends PrintRecord> list, ExportType type, String... args) throws Exception {
		exportFactory.getExport(type).export(list, args);
	}

}
