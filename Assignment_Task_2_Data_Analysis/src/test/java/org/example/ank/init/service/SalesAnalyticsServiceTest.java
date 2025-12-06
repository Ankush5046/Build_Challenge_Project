package org.example.ank.init.service;

import org.example.ank.init.model.SalesRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SalesAnalyticsServiceTest {

    private SalesRecord createRecord(String orderId,
                                     String region,
                                     String product,
                                     int quantity,
                                     double unitPrice) {
        return new SalesRecord(
                orderId,
                LocalDate.of(2025, 1, 1),
                region,
                product,
                "Category",
                quantity,
                unitPrice,
                "Ankush"
        );
    }

    @Test
    void testTotalRevenue() {
        List<SalesRecord> records = List.of(
                createRecord("1", "North", "Laptop", 2, 50000.0),
                createRecord("2", "South", "Phone", 1, 15000.0)
        );

        SalesAnalyticsService service = new SalesAnalyticsService();
        double totalRevenue = service.getTotalRevenue(records);

        assertEquals(2 * 50000.0 + 1 * 15000.0, totalRevenue, 0.001);
    }

    @Test
    void testTotalRevenueByRegion() {
        List<SalesRecord> records = List.of(
                createRecord("1", "North", "Laptop", 1, 100.0),
                createRecord("2", "North", "Phone", 2, 50.0),
                createRecord("3", "South", "Tablet", 1, 200.0)
        );

        SalesAnalyticsService service = new SalesAnalyticsService();
        Map<String, Double> revenueByRegion = service.getTotalRevenueByRegion(records);

        assertEquals(1 * 100.0 + 2 * 50.0, revenueByRegion.get("North"), 0.001);
        assertEquals(1 * 200.0, revenueByRegion.get("South"), 0.001);
    }

    @Test
    void testLargestOrder() {
        List<SalesRecord> records = List.of(
                createRecord("1", "North", "Laptop", 1, 100.0),
                createRecord("2", "North", "Phone", 3, 70.0)
        );

        SalesAnalyticsService service = new SalesAnalyticsService();
        var largestOpt = service.getLargestOrder(records);

        assertTrue(largestOpt.isPresent());
        assertEquals("2", largestOpt.get().getOrderId());
    }
}
