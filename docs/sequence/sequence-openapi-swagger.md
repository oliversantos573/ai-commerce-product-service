# Sequence Diagram — OpenAPI and Swagger Documentation


> **AI-Commerce Platform**
>
> **Service:** Product Service
>
> **Document:** Sequence Diagram — OpenAPI and Swagger Documentation
>
> **Version:** 1.0
>
> **Status:** Active


---

# Purpose

This document describes the OpenAPI and Swagger documentation generation flow adopted by the Product Service.

The objective is to define how REST API metadata is collected, processed and exposed as interactive API documentation.

The documentation process provides:

- Standardized API documentation
- Interactive API exploration
- Contract visibility
- Developer onboarding support
- API consumer integration support


---

# Scope

This sequence applies to the REST API documentation lifecycle.

The flow covers:

- Application startup
- OpenAPI configuration loading
- Controller discovery
- Endpoint metadata generation
- Swagger UI availability
- API consumer interaction


---

# Architecture Context


The OpenAPI documentation flow is part of the inbound API layer.


```text
REST Controllers

        ↓

Spring Application Context

        ↓

OpenAPI Generator

        ↓

OpenAPI Specification

        ↓

Swagger UI

        ↓

API Consumers