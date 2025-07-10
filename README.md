# 🏨 Hotel Management System  

A simple **Java console-based Hotel Management System** using **Maven**, **JDBC**, and **PostgreSQL**.

---
## Group Name 

 1. Abhijit Ashok Jadhav
 2. Om Bharat Babar

---

## College Name

  Solapur Education Society Solapur

  ---

  
## Description

A simple console-based Hotel Management System built using Java and Maven, designed to simulate hotel room booking and administration. Supports file handling and optionally PostgreSQL database with JDBC.

---

## 📌 Features

- View all rooms with booking status
- Book a room with customer name
- Check availability of a room
- Delete a room entry
- Automatic table creation and sample data insertion

---

## 🛠 Technologies Used

- Java (JDK 17+)
- Maven
- PostgreSQL (JDBC)
- Command-line UI (Console)

---

## 📂 Project Structure

```

hotel-management/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/hotel/
│       │       └── HotelManager.java
│       └── resources/
├── pom.xml

````

---

## Configure Database

* Make sure PostgreSQL is installed and running
* Create a database:

```sql
CREATE DATABASE hotel_db;
```

* Update your PostgreSQL username/password in:

```java
static final String DB_URL = "jdbc:postgresql://localhost:5432/hotel_db";
static final String USER = "postgres";
static final String PASS = "abhi93";
```

## Build and Run the App

```bash
mvn clean compile
mvn exec:java
```

---

## 📦 Dependencies

The `pom.xml` includes:

```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <version>42.7.1</version>
</dependency>
```

## 🖥 Prerequisites

Java 8 or higher

Apache Maven

PostgreSQL (optional, for DB version)

Git (optional)

---

## Output 

![WhatsApp Image 2025-07-10 at 19 46 36_74ba84fd](https://github.com/user-attachments/assets/7cfe80cf-1f14-421e-9f01-cd04719ddf5b)


<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/07782eb4-ec0a-42e7-bebe-27c16766b0b3" />

---
