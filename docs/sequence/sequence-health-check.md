# Sequence Diagram — Health Check


> **AI-Commerce Platform**
>
> **Service:** Product Service
>
> **Document:** Sequence Diagram — Health Check
>
> **Version:** 1.0
>
> **Status:** Active


---

# Purpose

This document describes the health check flow implemented by the Product Service.

The objective is to define how the application exposes its operational status and how external systems verify service availability.

The health check mechanism supports:

- Application availability monitoring
- Container orchestration readiness
- Infrastructure monitoring
- Load balancer validation
- Operational diagnostics


---

# Scope

This sequence applies to health verification requests executed against the Product Service.

The flow covers:

- Health endpoint invocation
- Application status verification
- Dependency validation
- Health response generation


---

# Architecture Context


The health check flow is part of the operational layer of the application.


```text
Monitoring System

        ↓

Health Endpoint

        ↓

Spring Boot Actuator

        ↓

Application Context

        ↓

Infrastructure Checks

        ↓

Health Response