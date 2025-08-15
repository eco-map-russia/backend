package com.example.eco_map.util.data;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractCsvImporter<T> {
    private final ResourceLoader resourceLoader;

    public void importLines(Function<String[], T> parser, String filePath) {
        Resource resource = resourceLoader.getResource(filePath);
        List<T> lines = new ArrayList<>();
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(';')
                .withQuoteChar('"')
                .build();

        try (InputStream is = resource.getInputStream();
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