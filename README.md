# jeap-opensearch-index-type

Core domain model for jEAP OpenSearch index types. Defines the contracts and data structures shared by index writers, search clients, and the index type registry Maven plugin.

## Overview

An **IndexType** describes one versioned OpenSearch index. It carries:

- `originType` — the business entity type (e.g. `PreziusRegistration`)
- `majorVersion` / `minorVersion` — independent versioning: major drives index rollover, minor drives mapping updates
- `indexWriteAlias` / `indexReadAlias` — the OpenSearch alias names used for writing and reading
- `mappingDefinition` — the field mapping as a JSON stream
- `dataClass` — the typed Java class that the `data` field is serialized as when writing to OpenSearch
- `roles` — optional list of jEAP roles required to read documents of this type

`IndexTypeDescriptor` is the non-generic parent interface used in APIs that do not need the typed `dataClass`.

## Key Types

| Type                   | Description                                                               |
|------------------------|---------------------------------------------------------------------------|
| `IndexType<T>`         | Full index type descriptor including the typed data class                 |
| `IndexTypeDescriptor`  | Non-generic descriptor (roles, aliases, origin type, versions)            |
| `Origin`               | Business object metadata (id, version, tenant, timestamps, URL reference) |
| `SearchItem<T>`        | Container pairing an `Origin` with typed business data                    |
| `SearchItemIndexed<T>` | `SearchItem` enriched with `SearchItemMetadata` written to OpenSearch     |
| `SearchItemMetadata`   | Index write timestamp and version stored in the `search_item` field       |

## Usage

Implement `IndexType<T>` and register it as a Spring bean. The jEAP OpenSearch infrastructure picks it up automatically at startup.

```java
@Component
public class MyDocumentIndexType implements IndexType<MyDocumentData> {
    @Override public String originType()      { return "MyDocument"; }
    @Override public int majorVersion()       { return 1; }
    @Override public int minorVersion()       { return 3; }
    @Override public String indexWriteAlias() { return "my_document_v1_write"; }
    @Override public String indexReadAlias()  { return "my_document_read"; }
    @Override public Class<MyDocumentData> dataClass() { return MyDocumentData.class; }
    @Override public Supplier<InputStream> mappingDefinition() {
        return () -> getClass().getResourceAsStream("/mappings/my_document_v1.json");
    }
}
```

## Build

```bash
mvn verify
```
