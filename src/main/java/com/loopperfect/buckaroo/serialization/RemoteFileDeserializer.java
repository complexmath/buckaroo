package com.loopperfect.buckaroo.serialization;

import com.google.common.base.Preconditions;
import com.google.common.hash.HashCode;
import com.google.gson.*;
import com.loopperfect.buckaroo.RemoteFile;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;

public final class RemoteFileDeserializer implements JsonDeserializer<RemoteFile> {

    @Override
    public RemoteFile deserialize(final JsonElement jsonElement, final Type type, final JsonDeserializationContext context) throws JsonParseException {

        Preconditions.checkNotNull(jsonElement);
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(context);

        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        if (!jsonObject.has("url")) {
            throw new JsonParseException("RemoteFile must have a URL. ");
        }
        final URI url = context.deserialize(jsonObject.get("url"), URI.class);

        if (!jsonObject.has("sha256")) {
            throw new JsonParseException("RemoteFile must have a sha256. ");
        }
        final HashCode sha256 = context.deserialize(jsonObject.get("sha256"), HashCode.class);

        return RemoteFile.of(url, sha256);
    }
}
