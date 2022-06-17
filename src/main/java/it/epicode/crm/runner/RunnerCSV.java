package it.epicode.crm.runner;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import it.epicode.crm.csv.ComuneCsv;
import it.epicode.crm.csv.ProvinciaCsv;
import it.epicode.crm.model.Comune;
import it.epicode.crm.model.Provincia;
import it.epicode.crm.repository.ComuneRepository;
import it.epicode.crm.repository.ProvinciaRepository;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
@Component
public class RunnerCSV implements ApplicationRunner {
	
ProvinciaRepository provinciaRepo;
ComuneRepository comuneRepo;
	
@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
		String fileProvinceItalianeCsv = "csv/province-italiane.csv";
		CsvSchema provinceItalianeCsvSchema = CsvSchema.emptySchema().withColumnSeparator(';').withHeader();
		CsvMapper mapper = new CsvMapper();
		File fileProvince = new ClassPathResource(fileProvinceItalianeCsv).getFile();
		MappingIterator<List <String>> valueReader = mapper
				.reader(ProvinciaCsv.class)
				.with(provinceItalianeCsvSchema)
				.readValues(fileProvince);
		for (Object o : valueReader.readAll()) {
			System.out.println(o);
			Provincia provincia = new Provincia();
			BeanUtils.copyProperties(o, provincia);
			provinciaRepo.save(provincia);
			
		}
		System.out.println("--------------------------------");	


		String fileComuniCsv = "csv/comuni.csv";
		CsvSchema comuniCsvSchema = CsvSchema.emptySchema().withColumnSeparator(';').withHeader();
		CsvMapper mapper2 = new CsvMapper();
		File fileComuni = new ClassPathResource(fileComuniCsv).getFile();
		MappingIterator<List <String>> valueReader2 = mapper2
				.reader(ComuneCsv.class)
				.with(comuniCsvSchema)
				.readValues(fileComuni);
		for (Object o : valueReader2.readAll()) {
			System.out.println(o);
			Comune comune = new Comune();
			BeanUtils.copyProperties(o, comune);
			comuneRepo.save(comune);
	

	}
}
}
