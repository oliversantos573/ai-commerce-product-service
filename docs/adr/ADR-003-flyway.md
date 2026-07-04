# ADR-003

# Title

Database Versioning with Flyway

---

## Status

Accepted

---

## Context

Database changes must be versioned and reproducible.

---

## Decision

Flyway is used for schema migrations.

Each database change must be represented by a new migration.

Existing migrations must never be modified after execution in shared environments.

---

## Consequences

Positive:

- Version control
- Safe deployments
- Automatic schema evolution