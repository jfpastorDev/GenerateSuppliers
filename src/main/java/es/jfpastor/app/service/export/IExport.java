package es.jfpastor.app.service.export;

import java.util.List;

import es.jfpastor.app.models.PrintRecord;

public interface IExport<T extends PrintRecord> {
	
	public ExportType getType();
	
	public void export(List<? extends PrintRecord> list, String... args) throws Exception;
}
