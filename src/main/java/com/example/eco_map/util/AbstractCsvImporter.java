package com.example.eco_map.util;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
                .withQuoteChar('"')
                .build();

        try (InputStream is = new ClassPathResource(filePath).getInputStream();
             InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReaderBuilder(isr).withCSVParser(csvParser).build()) {
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