# 🛒 Product API

A simple RESTful Product Management API built with Spring Boot. This service allows users to create, retrieve, update, and delete products.

# clone both the Inventory and Shopping services
## 📦 Features

- Create a new product
- View all products
- Get product by ID
- Update existing product
- Delete a product

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- Spring Boot
- MySQL (or your preferred DB)


### cURL for Postman testing


## create product curl
```bash
curl --location 'localhost:8081/shopping/product/create' \
--header 'Content-Type: application/json' \
--data '{
  "name": "apple",
  "price":10,
  "quantity": 340
}'
```
## get All product curl
```bash
curl --location --request GET 'localhost:8081/shopping/product/all' \
--header 'Content-Type: application/json' \
--data '{
  "name": "apple",
  "price": 120,
  "quantity": 340
}'
```
## getBy id procdut
```bash
curl --location --request GET 'localhost:8081/shopping/product/6' \
--header 'Content-Type: application/json' \
--data '{
  "name": "apple",
  "price": 120,
  "quantity": 340
}'
```
## update proudct
```bash
curl --location --request PUT 'localhost:8081/shopping/product/update/4' \
--header 'Content-Type: application/json' \
--data '{
  "name": "mango",
  
  "quantity": 541
}'
```
## delete product
```bash
curl --location --request DELETE 'localhost:8080/shopping/product/delete/6' \
--header 'Content-Type: application/json' \
--data '{
  "name": "apple",
  "price": 120,
  "quantity": 340
}'
```
