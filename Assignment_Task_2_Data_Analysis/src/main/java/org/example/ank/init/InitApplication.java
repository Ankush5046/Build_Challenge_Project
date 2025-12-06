package org.example.ank.init;

import org.example.ank.init.model.SalesRecord;
import org.example.ank.init.service.CsvReaderService;
import org.example.ank.init.service.SalesAnalyticsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Month;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class InitApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(InitApplication.class, args);
    }

    @Override
    public void run(String... args) {
        CsvReaderService csvReaderService = new CsvReaderService();
        SalesAnalyticsService analyticsService = new SalesAnalyticsService();

        List<SalesRecord> records = csvReaderService.loadSalesData("sales-data.csv");

        System.out.println(" SALES ANALYTICS REPORT ");

        double totalRevenue = analyticsService.getTotalRevenue(records);
        System.out.println("Total Revenue: " + totalRevenue);

        System.out.println("Total Revenue by Region:");
        Map<String, Double> revenueByRegion = analyticsService.getTotalRevenueByRegion(records);
        revenueByRegion.forEach((region, revenue) ->
                System.out.println("  " + region + " -> " + revenue)
        );

        System.out.println("Units Sold by Product:");
        Map<String, Integer> unitsByProduct = analyticsService.getUnitsSoldByProduct(records);
        unitsByProduct.forEach((product, units) ->
                System.out.println("  " + product + " -> " + units)
        );

        System.out.println("Average Order Value: " + analyticsService.getAverageOrderValue(records));

        System.out.println("Largest Order:");
        analyticsService.getLargestOrder(records)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("No records found")
                );

        System.out.println("Revenue by Sales Person:");
        Map<String, Double> revenueBySalesPerson = analyticsService.getRevenueBySalesPerson(records);
        revenueBySalesPerson.forEach((salesPerson, revenue) ->
                System.out.println("  " + salesPerson + " -> " + revenue)
        );

        System.out.println("Revenue by Month:");
        Map<Month, Double> revenueByMonth = analyticsService.getRevenueByMonth(records);
        revenueByMonth.forEach((month, revenue) ->
                System.out.println("  " + month + " -> " + revenue)
        );

        System.out.println("Order Value Statistics:");
        System.out.println("  " + analyticsService.getOrderValueStatistics(records));

        System.out.println("END OF REPORT");
    }
}
