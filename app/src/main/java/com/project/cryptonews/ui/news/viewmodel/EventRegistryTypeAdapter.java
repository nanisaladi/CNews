package com.project.cryptonews.ui.news.viewmodel;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.project.cryptonews.data.eventregistry.Result;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Event Registry type adapter.
 */

public class EventRegistryTypeAdapter implements JsonDeserializer<List<Result>> {

    @Override
    public List<Result> deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
        List<Result> results = new ArrayList<>();
        Gson gson = new Gson();
        Result[] results1 = null;
        if(json.isJsonObject()) {
            JsonElement elements = json.getAsJsonObject().get("results");
            if(elements.isJsonArray()) {
                results1 = gson.fromJson(elements, Result[].class);
            }
        }

        results.clear();
        results.addAll(Arrays.asList(results1));

        return results;
    }
}
