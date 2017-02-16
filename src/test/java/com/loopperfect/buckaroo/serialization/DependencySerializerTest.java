package com.loopperfect.buckaroo.serialization;

import com.google.gson.Gson;
import com.loopperfect.buckaroo.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class DependencySerializerTest {

    private static void serializeDeserialize(final Dependency dependency) {

        final Gson gson = Serializers.gson();

        final String serializedDependency = gson.toJson(dependency);

        final Dependency deserizializedDependency = gson.fromJson(serializedDependency, Dependency.class);

        assertEquals(dependency, deserizializedDependency);
    }

    @Test
    public void testDependencySerializer1() {

        serializeDeserialize(Dependency.of(
                Identifier.of("magic-libs"),
                ExactSemanticVersion.of(SemanticVersion.of(3, 4))));
    }

    @Test
    public void testDependencySerializer2() {

        serializeDeserialize(Dependency.of(
                Identifier.of("magic-libs"),
                ExactSemanticVersion.of(SemanticVersion.of(3, 4), SemanticVersion.of(6, 5, 1))));
    }

    @Test
    public void testDependencySerializer3() {

        serializeDeserialize(Dependency.of(
                Identifier.of("magic-libs"),
                AnySemanticVersion.of()));
    }

    @Test
    public void testDependencySerializer4() {

        serializeDeserialize(Dependency.of(
                Identifier.of("magic-libs"),
                BoundedSemanticVersion.of(SemanticVersion.of(4), AboveOrBelow.ABOVE)));
    }
}