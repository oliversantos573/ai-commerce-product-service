# ADR-004

# Title

PostgreSQL as Primary Database

---

## Status

Accepted

---

## Context

The service requires a relational database with strong consistency.

---

## Decision

Use PostgreSQL.

Reasons:

- ACID
- Mature ecosystem
- Excellent Spring support
- High performance
- Open Source

---

## Consequences

The persistence layer is optimized for relational modeling.