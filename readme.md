# NameClassifierAPI

A RESTful API that integrates with the Genderize API to classify names and return a structured, processed response.

---

## Project Structure

```
name-classifier-api/
│
├── src/
│   ├── main/
│   │   ├── java/com/hng/task/
│   │   │   ├── controller/
│   │   │   │   └── GenderizeController.java
│   │   │   │
│   │   │   ├── service/
│   │   │   │   └── ClassificationService.java
│   │   │   │
│   │   │   ├── dto/
│   │   │   │   ├── GenderizeResponse.java
│   │   │   │   └── GenderizeRaw.java
│   │   │   │
│   │   │   ├── exceptions/
│   │   │   │   ├── CustomBadRequestException.java 
│   │   │   │   ├── CustomNotfoundException.java
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── UnprocessableEntityException.java
│   │   │   │
│   │   │   ├── util/
│   │   │   │   └── ApiResponse.java 
│   │   │   │
│   │   │   ├──config/
│   │   │   │   ├── RestTemplateConfig.java        
│   │   │   │   ├── SwaggerConfig.java  
│   │   │   │   └── WebConfig.java  
│   │   │   │         
│   │   │   ├── NameClassifierApiApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│
├── README.md
└── pom.xml
```

## 🚀 Features

* Accepts a name via query parameter
* Integrates with the Genderize external API
* Processes and transforms the API response
* Computes confidence level based on probability and sample size
* Handles edge cases and errors gracefully
* Returns standardized JSON responses

---

## 📡 Endpoint

### GET /api/classify?name={name}

#### Example Request

```
GET /api/classify?name=esther
```

---

## ✅ Success Response (200 OK)

```json
{
  "status": "success",
  "data": {
    "name": "esther",
    "gender": "female",
    "probability": 0.99,
    "sample_size": 1234,
    "is_confident": true,
    "processed_at": "2026-04-11T10:30:00Z"
  }
}
```

---

## ❌ Error Responses

### 400 Bad Request

```json
{
  "status": "error",
  "message": "Name parameter is required"
}
```

### 422 Unprocessable Entity

```json
{
  "status": "error",
  "message": "No prediction available for the provided name"
}
```

### 502 Bad Gateway

```json
{
  "status": "error",
  "message": "Upstream service failed"
}
```

---

## 🧠 Processing Logic

* Extracts:

    * gender
    * probability
    * count → renamed to sample_size

* Computes:

```
is_confident = probability >= 0.7 AND sample_size >= 100
```

* Adds:

    * processed_at (UTC timestamp in ISO 8601 format)

---

## ⚙️ Tech Stack

* Java
* Spring Boot
* REST APIs

---

## 🌍 Deployment

The API is deployed and publicly accessible.

Base URL:

```
https://genderizeapi-production-e088.up.railway.app
```

---

## 🧪 Testing

Test with:

```
/api/classify?name=emma
/api/classify?name=grace
/api/classify?name=esther
```

---

## 📌 Notes

* CORS enabled for all origins
* Handles multiple requests efficiently
* Response time optimized (excluding external API latency)

---

