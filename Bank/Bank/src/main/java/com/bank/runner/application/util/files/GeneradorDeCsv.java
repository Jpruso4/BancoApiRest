package com.bank.runner.application.util.files;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.bank.runner.application.util.Constantes;

public class GeneradorDeCsv {
	public <T> String CrearArchivo(List<T> listCsvs, HttpServletResponse response) throws IOException {
		
		String[] cvsHeader = null;
		String[] nameMapping = null;
		String headerValue = null;

		response.setContentType("text/csv");

		String headerKey = "Content-Disposition";	

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		if (listCsvs.isEmpty()) {
			return "No se puede generar el csv porque la lista esta vacia";
		}

		switch (Types.getType(listCsvs.get(0))) {
			case MOVIMIENTO:
				headerValue = "attachment; filename=Lista de Movimientos.csv";
				final String[] csvHeader = Constantes.csvHeaderMovimiento;
				nameMapping = Constantes.nameMappingMovimiento;
				csvWriter.writeHeader(csvHeader);
				break;
			case CLIENTE:
				headerValue = "attachment; filename=Lista de Clientes.csv";
				cvsHeader = Constantes.csvHeaderCliente;
				nameMapping = Constantes.nameMappingCliente;
				break;
			default:
				break;
		}
		
		response.setHeader(headerKey, headerValue);
		csvWriter.writeHeader(cvsHeader);
		

		for (T modeloCvs : listCsvs) {
				csvWriter.write(modeloCvs, nameMapping);	
		}

		csvWriter.close();

		return csvWriter.toString();
	}
}
