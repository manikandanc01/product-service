
# 🛍️ Product Service - E-commerce API

This is a simple **Product Service API** built as part of an e-commerce platform. Currently, it integrates with the **FakeStore API** to simulate product-related operations such as:

- Fetching all products
- Fetching product by ID
- Creating a new product
- Updating an existing product

Future versions will integrate a **custom database** for full control over the data.

---

## ⚙️ Features (Current)

- 🔹 `GET /products` — Fetch all products (via FakeStore API)
- 🔹 `GET /products/{id}` — Fetch product by ID
- 🔹 `POST /products` — Add a new product
- 🔹 `PUT /products/{id}` — Update product by ID

---

## 🚧 Upcoming

- [ ] Switch from FakeStore API to internal database
- [ ] Add validation and authentication
- [ ] Add pagination and filtering support
- [ ] Add proper logging and test cases

---

## 🔀 Branching Strategy

- `main`: Production-ready code
- `feature/fakestore-api`: Third-party API integration
- `feature/database`: Internal database integration *(coming soon)*

---

## 📦 Tech Stack

- Java 21+
- Spring Boot
- REST APIs
- FakeStore API (external simulation)
- (Coming Soon) MySQL or PostgreSQL

---

## 🔗 External API Used

- [FakeStore API Docs](https://fakestoreapi.com/)

---




