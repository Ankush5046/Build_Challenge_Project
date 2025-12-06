# 🚀 Java Build Challenge – Multi-Assignment Repository

This repository contains the complete implementation of two mandatory Java coding challenge assignments as part of the Build Challenge. Each assignment demonstrates core software engineering concepts including:

 ✅ Multi-threading & synchronization  
 ✅ Functional programming  
 ✅ Java Streams & Lambda expressions  
 ✅ Data aggregation and grouping  
 ✅ Unit testing with JUnit  
 ✅ Console-based execution  

All implementations follow best coding practices, include fully working source code, and are backed by unit tests.



## 📁 Projects Overview

This repository contains two independent Java projects:

✅ Assignment 1: Producer–Consumer Pattern with Thread Synchronization

🔹 Short Description
Implements the classic Producer–Consumer problem using Java multithreading and synchronization.

🔹 Key Testing Objectives
- Thread synchronization  
- Concurrent programming  
- Blocking queues  
- `wait()` / `notify()` mechanism  

🔹 Detailed Description
A Producer thread reads data from a source container and places items into a shared buffer/queue, while a Consumer thread retrieves data from the queue and stores it into a destination container. Proper synchronization ensures safe inter-thread communication without data corruption.

🔹 Technologies Used
- Java Threads
- `synchronized` blocks
- `wait()` / `notify()`
- JUnit Testing


✅ Assignment 2: CSV Data Analytics Using Java Streams

🔹 Short Description
Performs data analysis on CSV sales data using Java Streams API and Functional Programming**.

🔹 Key Testing Objectives
- Functional programming  
- Stream operations  
- Data aggregation  
- Lambda expressions  

🔹 Detailed Description
This application reads sales data from a CSV file and performs multiple analytical queries using Java Streams, including aggregation, grouping, filtering, and statistical analysis.

 🔹 Analytics Performed
- ✅ Total revenue calculation  
- ✅ Revenue grouped by region  
- ✅ Units sold per product  
- ✅ Average order value  
- ✅ Largest order detection  
- ✅ Revenue by salesperson  
- ✅ Monthly revenue analysis  
- ✅ Summary statistics (min, max, avg, count, sum)  

All results are printed directly to the console.



🧪 Unit Testing

- ✅ Implemented using JUnit 5
- ✅ All analytical methods are covered with test cases
- ✅ Ensures correctness of calculations and logic

Run all tests using:

```bash
mvn test
