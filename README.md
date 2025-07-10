Here’s a ready-to-use `README.md` file for your **Hotel Management Maven Java project** — including features, setup, and usage instructions. You can copy this into your project folder or let me add it to your ZIP if needed.

---

## ✅ `README.md`

```markdown
# 🏨 Hotel Management System - Java Maven Project

A simple **Java console-based Hotel Management System** using **Maven**, **JDBC**, and **PostgreSQL**.

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

## 🚀 How to Run

### 1. Clone the Repo or Download ZIP
```bash
git clone https://github.com/yourusername/hotel-management.git
cd hotel-management
````

### 2. Configure Database

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

### 3. Build and Run the App

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
