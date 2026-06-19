# jeap-opensearch-index-type

Core domain model for jEAP OpenSearch index types. Defines the contracts and data structures
shared by index writers, search clients, and the index type registry Maven plugin.

## Key Types

- **`IndexType<T>`** — full index type descriptor including the typed data class for the `data` field
- **`IndexTypeDescriptor`** — non-generic descriptor: system, origin type, versions, alias names, roles, and mapping
- **`Origin`** — business object metadata written to the `origin` field of every OpenSearch document
- **`SearchItem<T>`** — pairs an `Origin` with typed business data; returned by the SearchItem Provider API
- **`SearchItemIndexed<T>`** — `SearchItem` enriched with `SearchItemMetadata` written by the index writer
- **`SearchItemMetadata`** — `upserted_at`, `major_version`, and `minor_version` stored in `search_item`

## Documentation

- [Getting started](docs/getting-started.md)
- [Domain model](docs/domain-model.md)

## Note

This repository is part of the open source distribution of jEAP. See [github.com/jeap-admin-ch/jeap](https://github.com/jeap-admin-ch/jeap) for more information.

## License

This repository is Open Source Software licensed under the [Apache License 2.0](./LICENSE).
