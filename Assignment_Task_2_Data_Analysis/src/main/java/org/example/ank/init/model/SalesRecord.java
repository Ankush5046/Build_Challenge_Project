package org.example.ank.init.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalesRecord {

    private final String orderId;
    private final LocalDate date;
    private final String region;
    private final String product;
    private final String category;
    private final int quantity;
    private final double unitPrice;
    private final String salesPerson;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SalesRecord(String orderId,
                       LocalDate date,
                       String region,
                       String product,
                       String category,
                       int quantity,
                       double unitPrice,
                       String salesPerson) {
        this.orderId = orderId;
        this.date = date;
        this.region = region;
        this.product = product;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.salesPerson = salesPerson;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getRegion() {
        return region;
    }

    public String getProduct() {
        return product;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public double getOrderValue() {
        return quantity * unitPrice;
    }

    public static SalesRecord fromCsvLine(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length < 8) {
            throw new IllegalArgumentException("Invalid CSV line: " + line);
        }
        String orderId = parts[0].trim();
        LocalDate date = LocalDate.parse(parts[1].trim(), DATE_FORMATTER);
        String region = parts[2].trim();
        String product = parts[3].trim();
        String category = parts[4].trim();
        int quantity = Integer.parseInt(parts[5].trim());
        double unitPrice = Double.parseDouble(parts[6].trim());
        String salesPerson = parts[7].trim();

        return new SalesRecord(orderId, date, region, product, category, quantity, unitPrice, salesPerson);
    }

    @Override
    public String toString() {
        return "SalesRecord{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", region='" + region + '\'' +
                ", product='" + product + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", salesPerson='" + salesPerson + '\'' +
                '}';
    }
}
