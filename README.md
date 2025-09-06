
# Group Buy Project

### 📌 Overview

Group Buy Project is a Spring Boot-based backend system designed to support group-buying business scenarios. It provides core capabilities such as discount calculation, group-buy settlement, payment callback handling, and message distribution.
The project follows a Domain-Driven Design (DDD) layered architecture to achieve clear separation of concerns, scalability, and maintainability.

### 🚀 Key Features

* Domain-Driven Design (DDD): Clear boundaries between application, domain, and infrastructure layers.

* High-Concurrency Idempotency: Redis-based distributed locks to prevent duplicate settlement execution.

* Asynchronous Decoupling: RabbitMQ (topic mode) for decoupling payment service, service and group-buy services.

* Pluggable Rule Engine: Flexible settlement rule chain with dynamic extension support.

* Engineering Practices: Multi-module Maven project with unified exception handling and configuration management.

### 🏗️ Architecture
```html
Application Layer       -> Application configuration and orchestration
API Layer               -> REST APIs Related (DTO, API)
Domain Layer            -> Core business logic
Trigger Layer           -> External triggers such as HTTP requests, job scheduling, event listeners
Types Layer             -> Shared definitions (exceptions, enums, design pattern interfaces)
Infrastructure Layer    -> Database / Redis / Message Queue
```


### 🔧 Tech Stack
* Language/Frameworks: Java 17, Spring Boot, MyBatis
* Infrastructure: MySQL, Redis, RabbitMQ
* Build/Management: Maven (multi-module project)

### 📝 What I Learned
Through this project, I gained experience in:

* Translating business requirements into scalable technical solutions

* Ensuring consistency and idempotency in distributed systems

* Applying message-driven architecture for system decoupling

* Designing modular, extensible backend systems with engineering best practices