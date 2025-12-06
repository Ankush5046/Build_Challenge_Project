package org.example.ank.init.service;

import org.example.ank.init.model.SalesRecord;

import java.time.Month;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SalesAnalyticsService {

    public double getTotalRevenue(List<SalesRecord> records) {
        return records.stream()
                .mapToDouble(SalesRecord::getOrderValue)
                .sum();
    }

    public Map<String, Double> getTotalRevenueByRegion(List<SalesRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getRegion,
                        Collectors.summingDouble(SalesRecord::getOrderValue)
                ));
    }

    public Map<String, Integer> getUnitsSoldByProduct(List<SalesRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getProduct,
                        Collectors.summingInt(SalesRecord::getQuantity)
                ));
    }

    public double getAverageOrderValue(List<SalesRecord> records) {
        return records.stream()
                .mapToDouble(SalesRecord::getOrderValue)
                .average()
                .orElse(0.0);
    }

    public Optional<SalesRecord> getLargestOrder(List<SalesRecord> records) {
        return records.stream()
                .max(Comparator.comparingDouble(SalesRecord::getOrderValue));
    }

    public Map<String, Double> getRevenueBySalesPerson(List<SalesRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getSalesPerson,
                        Collectors.summingDouble(SalesRecord::getOrderValue)
                ));
    }

    public Map<Month, Double> getRevenueByMonth(List<SalesRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getDate().getMonth(),
                        Collectors.summingDouble(SalesRecord::getOrderValue)
                ));
    }

    public DoubleSummaryStatistics getOrderValueStatistics(List<SalesRecord> records) {
        return records.stream()
                .mapToDouble(SalesRecord::getOrderValue)
                .summaryStatistics();
    }
}
