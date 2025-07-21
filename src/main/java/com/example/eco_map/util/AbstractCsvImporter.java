package com.example.eco_map.util;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractCsvImporter<T> {

    public void importLines(Function<String[], T> parser, String filePath) {
        List<T> lines = new ArrayList<>();
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(';')
                .withSeparator(',')
                .withQuoteChar('"')
                .build();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(csvParser)
                .build()) {
            reader.readNext();
            String[] line;

            while ((line = reader.readNext()) != null) {
                try {
                    T record = parser.apply(line);
                    if (record != null) lines.add(record);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }

            saveAll(lines);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    protected abstract void saveAll(List<T> records);
}