# ADR-006

# Title

Soft Delete Strategy

---

## Status

Accepted

---

## Context

Products should not be permanently removed for auditing and traceability.

---

## Decision

Deletion changes the product status instead of physically removing the record.

Possible statuses:

- DRAFT
- ACTIVE
- INACTIVE
- DELETED

---

## Consequences

Advantages:

- Auditability
- Data recovery
- Historical analysis

Trade-off:

Queries must ignore deleted records when appropriate.