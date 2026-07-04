# ADR-008

# Title

Clean Architecture

---

## Status

Accepted

---

## Context

The application should separate business logic from infrastructure concerns.

---

## Decision

Adopt Clean Architecture with clear layers:

- Domain
- Application
- Adapters
- Infrastructure

Dependencies always point inward.

---

## Consequences

Benefits:

- Framework independence
- Easier testing
- Better maintainability
- Long-term scalability