
# Group Buy Project

### 📌 Overview

Group Buy Project is a Spring Boot-based backend system designed to support group-buying business scenarios. It provides core capabilities such as discount calculation, group-buy settlement, payment callback handling, and message distribution.
The project follows a Domain-Driven Design (DDD) layered architecture to achieve clear separation of concerns, scalability, and maintainability.

### 🚀 Key Features
 

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
Infrastructure Layer    -> Database/ Redis/ rabbitMq/ okhttp
```

### 🎯 Use Cases & Functional Requirements
| Use Case (UC)                                 | Functional Requirement (FR)                                                                                                                                                                    |
|-----------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| UC1: Browse Group-buy Product                 | FR1.1: The system must allow users to browse the discounted price of each group-buy product, which is calculated upon activity details, discount information, and product details.             |
|                                               | FR1.2: The system must allow users to view available products with associated activity and discount information.                                                                               |
|                                               | FR1.3: The system must ensure that the response time does not exceed **150 ms**.                                                                                                               |
| UC2: Collect Crowd Tag                        | FR2.1:                                                                                                                                                                                         |
| UC3: Configure Activity Parameter Dynamically | FR3.1:                                                                                                                                                                                         | 
| UC4: Place Group-buy Order                    | FR4.1: The system must lock the group-buy order first when placing group-buy order                                                                                                             |
|                                               | FR4.2: The system shall verify whether a user is eligible to place a group-buy order before locking it (verify activity valid time period)                                                     |
|                                               | FR4.3: The system shall verify if every group member has finished their payment after receiving the callback message.                                                                          |
|                                               | FR4.4: The system shall validate the group-buy order conditions, including channel/ source validity and payment time within the activity period, before verifying group-buy order eligibility. |
|                                               | FR4.5: The system shall implement a task callback mechanism to notify external microservices, with a job compensation mechanism to handle failures or retries.                                                                                               |

### 🔧 Tech Stack
* Language/Frameworks: Java 17, Spring Boot, MyBatis
* Infrastructure: MySQL, Redis, RabbitMQ, HttpOk
* Build/Management: Maven (multi-module project)

### 📝 What I Learned
Through this project, I gained experience in:

* Translating business requirements into scalable technical solutions

* Ensuring consistency and idempotency in distributed systems

* Designing modular, extensible backend systems DDD architecture to reduce coupling within layer. 

* Applied design pattern framework(such as Responsibility Chain) to improve code flexibility and scalability.

* Implemented communication between microservices. 