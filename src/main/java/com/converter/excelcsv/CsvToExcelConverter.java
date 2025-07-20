package com.converter.excelcsv;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.opencsv.CSVReader;
import org.springframework.web.multipart.MultipartFile; 

import java.io.*;
import java.util.List;

public class CsvToExcelConverter {

    public static byte[] convert(MultipartFile file) throws IOException, com.opencsv.exceptions.CsvException {
        try (InputStream inputStream = file.getInputStream();
             CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
             Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Sheet1");
            List<String[]> csvLines = reader.readAll();

            int rowIndex = 0;
            for (String[] line : csvLines) {
                Row row = sheet.createRow(rowIndex++);
                for (int i = 0; i < line.length; i++) {
                    row.createCell(i).setCellValue(line[i]);
                }
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }
}
