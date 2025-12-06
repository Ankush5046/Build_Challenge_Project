package org.example.ank.init.service;

import org.example.ank.init.model.SalesRecord;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvReaderService {

    public List<SalesRecord> loadSalesData(String resourceName) {
        try {
            URL url = getClass().getClassLoader().getResource(resourceName);
            if (url == null) {
                throw new IllegalArgumentException("Resource not found: " + resourceName);
            }
            Path path = Paths.get(url.toURI());

            try (Stream<String> lines = Files.lines(path)) {
                return lines
                        .skip(1) // skip header
                        .filter(line -> !line.isBlank())
                        .map(SalesRecord::fromCsvLine)
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Error reading CSV file", e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Invalid URI for resource: " + resourceName, e);
        }
    }
}
