package ch.admin.bit.jeap.opensearch.indextype;

import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

public interface IndexTypeDescriptor {

    /**
     * @return the system to which the index type belongs to
     */
    String system();

    /**
     * @return the type of the business object to be indexed
     */
    String originType();

    /**
     * @return the major version of this index type
     */
    int majorVersion();

    /**
     * @return the minor version of this index type
     */
    int minorVersion();

    /**
     * @return description of this index type
     */
    String description();

    /**
     * @return url where to find the documentation of this index type
     */
    String documentationUrl();

    /**
     * @return the roles that are authorized to read this index type
     */
    List<String> roles();

    /**
     * @return a supplier of fresh streams containing the native OpenSearch JSON definition:
     *         required {@code mappings} and optional {@code settings.analysis}. Operational settings
     *         remain writer-owned. Mapping and analysis are versioned together. The caller closes each stream.
     */
    Supplier<InputStream> mappingDefinition();

    default String version() {
        return majorVersion() + "." + minorVersion();
    }

    default String indexWriteAlias() {
        return toSnakeCase(originType()) + "_v" + majorVersion() + "_write";
    }

    default String indexReadAlias() {
        return toSnakeCase(originType()) + "_read";
    }

    private static String toSnakeCase(String name) {
        return name
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z\\d])([A-Z])", "$1_$2")
                .toLowerCase();
    }
}
