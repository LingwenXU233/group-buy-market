
# Group Buy Project

### 📌 Overview

Group Buy Project is a Spring Boot-based backend system designed to support group-buying business scenarios. It provides core capabilities such as discount calculation, group-buy settlement, payment callback handling, and message distribution.
The project follows a Domain-Driven Design (DDD) layered architecture to achieve clear separation of concerns, scalability, and maintainability.

### 🚀 Key Features

* Services Decoupling: RabbitMQ for decoupling payment service, service and group-buy services.
* High-Concurrency Idempotency: Redis-based distributed locks to prevent duplicate settlement execution.
* DCC 
* Transaction Consistency: Ensured atomicity of order creation and order status updating through database transactions, preventing data inconsistency.
* Performance Optimization: Used RabbitMQ + async callbacks in group-buy settlement to improve throughput and responsiveness.
* Engineering Practices: Multi-module Maven project with unified exception handling management, improving maintainability and code quality.

### 🏗️ Architecture
```html
Application Layer       -> Application configuration and orchestration
API Layer               -> REST APIs Related (DTO, API)
Domain Layer            -> Core business logic
Trigger Layer           -> External triggers such as HTTP requests, job scheduling, event listeners
Types Layer             -> Shared definitions (exceptions, enums, design pattern interfaces)
Infrastructure Layer    -> Database/ Redis/ rabbitMq/ okhttp
```

### 🎯 Functional Requirements & Implementation Detail
| **Functional Requirement**             | **Implementation Detail**                                                                                                                                                                                                                                                                                                           |
|----------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| FR1: Product Discount Calculation      | Applied the Rule Tree design pattern to decouple complex discount logic; <br/> Used multi-threaded asynchronous data retrieval to improve service API responsiveness; <br/> Adopted Strategy Pattern with dependency injection to flexibly support multiple discount types.                                                         |
| FR2: Crowd Tagging                     | Stored and updated user tag info with Redis Bitmap for efficient large-scale queries.                                                                                                                                                                                                                                               |
| FR3: Dynamic Activity Parameter Update | Introduced the Dynamic Configuration Control (DCC) component to enable dynamic configuration parameter updates (like source/ channel restriction, crowd tag restriction), avoiding costly server restarts.                                                                                                                          |
| FR4: Order Locking                     | Implemented order locking before payment to prevent overselling; <br/> Applied idempotency validation in order locking procedure to avoid duplicate requests.                                                                                                                                                                       |
| FR5: Group-buy Settlement              | Used Chain of Responsibility Pattern to handle frequently changing settlement rules; <br/> Integrated RabbitMQ and local multi-thread async for callback tasks, with scheduled compensation for failed or missed callbacks; <br/> Applied Redis-based distributed lock to prevent repeated execution of the same notification task. |

### 🔧 Tech Stack
* Language/Frameworks: Java 17, Spring Boot, MyBatis
* Infrastructure: MySQL, Redis, RabbitMQ, HttpOk
* Build/Management: Maven (multi-module project)

### 📝 What I Learned
Through this project, I gained experience in:

* Translating business requirements into scalable technical solutions.

* Designing modular, extensible backend systems DDD architecture to reduce coupling within layer.

* Implementing microservices communication with rabbitMq. 

* Applied design pattern framework(such as Chain of Responsibility) to improve code flexibility and scalability.
