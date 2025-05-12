package ru.janeryshouse.orderservice.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Optional;

public class GsonOptionalDeserializer<T> implements JsonDeserializer<Optional<T>>, JsonSerializer<Optional<T>> {
    @Override
    public Optional<T> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final T value = jsonDeserializationContext.deserialize(jsonElement, type);
        return Optional.ofNullable(value);
    }

    @Override
    public JsonElement serialize(Optional<T> t, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(t.orElse(null));
    }
}
