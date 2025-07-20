package com.converter.excelcsv;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.*;
import java.io.*;
import com.opencsv.exceptions.CsvException;


@RestController
@RequestMapping("/convert")
public class FileConverterController {

    @PostMapping("/csv-to-excel")
    public ResponseEntity<byte[]> convertCsvToExcel(@RequestParam("file") MultipartFile file) throws IOException, CsvException  {
        byte[] excelBytes = CsvToExcelConverter.convert(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=converted.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelBytes);
    }

    @PostMapping("/excel-to-csv")
    public ResponseEntity<byte[]> convertExcelToCsv(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] csvBytes = ExcelToCsvConverter.convert(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=converted.csv")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(csvBytes);
    }
}
