# ADR-001

# Title

Adoption of Hexagonal Architecture

---

## Status

Accepted

---

## Context

The project required a flexible architecture that isolates the business domain from external technologies.

The objective is to prevent business rules from depending on frameworks, databases or APIs.

----

## Decision

The project adopts Hexagonal Architecture (Ports and Adapters).

Core principles:

- Domain independent of frameworks
- Communication through ports
- External systems implemented as adapters
- Dependency inversion

---

## Consequences

Positive:

- High maintainability
- Easier testing
- Infrastructure independence
- Better modularity

Negative:

- More classes
- Slightly higher initial complexity

---

## References

- Alistair Cockburn
- Ports and Adapters Architecture