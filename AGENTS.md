# AGENTS.md — jeap-opensearch-index-type

## Project purpose
Core domain model for jEAP OpenSearch index types. Zero infrastructure dependencies — only Jackson for JSON serialization.

## Module layout
Single Maven module. All types live in `ch.admin.bit.jeap.opensearch.indextype`.

## Key interfaces
- `IndexTypeDescriptor` — non-generic, used in public APIs to avoid wildcard return types (Sonar S1452)
- `IndexType<T>` extends `IndexTypeDescriptor` — adds `dataClass()` for typed deserialization

## Conventions
- All types are records (immutable value objects)
- `SearchItemIndexed` is the write model (what goes into OpenSearch); `SearchItem` is the read model (what comes back from the SearchItem provider)
- `SearchItemMetadata` is always serialized under the `search_item` JSON key via `@JsonProperty`
- `IndexTypeDescriptor` must remain non-generic so it can be used as a return type without wildcards

## Build & test
```bash
mvn verify
```
No Spring context, no Testcontainers — pure unit tests only.
