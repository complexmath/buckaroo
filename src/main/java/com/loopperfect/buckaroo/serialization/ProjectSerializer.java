package com.loopperfect.buckaroo.serialization;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.loopperfect.buckaroo.Identifier;
import com.loopperfect.buckaroo.Project;
import com.loopperfect.buckaroo.SemanticVersionRequirement;

import java.lang.reflect.Type;
import java.util.Map;

public final class ProjectSerializer implements JsonSerializer<Project> {

    @Override
    public JsonElement serialize(final Project project, final Type type, final JsonSerializationContext context) {

        Preconditions.checkNotNull(project);
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(context);

        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", project.name.name);

        if (project.license.isPresent()) {
            jsonObject.addProperty("license", project.license.get().toString());
        }

        final JsonObject dependenciesObject = new JsonObject();

        for (final Map.Entry<Identifier, SemanticVersionRequirement> i : project.dependencies.entrySet()) {
            dependenciesObject.addProperty(i.getKey().name, i.getValue().encode());
        }

        jsonObject.add("dependencies", dependenciesObject);

        return jsonObject;
    }
}