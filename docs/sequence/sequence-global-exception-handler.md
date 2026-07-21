# Sequence Diagram — Global Exception Handler


> **AI-Commerce Platform**
>
> **Service:** Product Service
>
> **Document:** Sequence Diagram — Global Exception Handler
>
> **Version:** 1.0
>
> **Status:** Active


---

# Purpose

This document describes the global exception handling flow implemented by the Product Service REST API.

The objective is to define how application and domain exceptions are intercepted, transformed and returned to API consumers through a standardized error response format.

The Global Exception Handler provides:

- Centralized exception management
- Consistent HTTP error responses
- Separation between domain errors and HTTP concerns
- Improved API reliability
- Predictable client behavior


---

# Scope

This sequence applies to all REST endpoints exposed by the Product Service.

Covered scenarios:

- Product not found
- Product already exists
- Request validation failure
- Unexpected system exceptions


---

# Architecture Context


The exception handling flow follows the Hexagonal Architecture boundaries.


```text
Client

  ↓

REST Controller

  ↓

Application Layer

  ↓

Domain Layer

  ↓

Domain Exception

  ↓

Global Exception Handler

  ↓

ErrorResponse

  ↓

HTTP Response

  ↓

Client