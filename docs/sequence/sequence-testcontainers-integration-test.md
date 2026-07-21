# Sequence Diagram — Testcontainers Integration Test


> **AI-Commerce Platform**
>
> **Service:** Product Service
>
> **Document:** Sequence Diagram — Testcontainers Integration Test
>
> **Version:** 1.0
>
> **Status:** Active


---

# Purpose

This document describes the integration testing flow using Testcontainers in the Product Service.

The objective is to demonstrate how automated integration tests execute against real infrastructure dependencies using disposable containers.

The Testcontainers strategy provides:

- Real database validation
- Production-like test environments
- Isolated execution
- Reliable integration testing
- Environment consistency


---

# Scope

This sequence applies to integration tests executed during the Maven verification lifecycle.

The flow covers:

- Test execution initialization
- Docker container provisioning
- PostgreSQL startup
- Spring Boot context initialization
- Database migration
- Integration test execution
- Container cleanup


---

# Architecture Context


Integration tests validate the interaction between application components and external infrastructure.


```text
Integration Test

        ↓

Spring Boot Test Context

        ↓

Application Components

        ↓

Persistence Adapter

        ↓

Testcontainers PostgreSQL

        ↓

Database Container