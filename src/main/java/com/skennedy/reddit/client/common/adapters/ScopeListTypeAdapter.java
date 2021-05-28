package com.skennedy.reddit.client.common.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.skennedy.reddit.client.common.model.Scope;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScopeListTypeAdapter extends TypeAdapter<List<Scope>> {

    @Override
    public void write(JsonWriter out, List<Scope> scope) throws IOException {
        if (scope == null) {
            out.nullValue();
        } else {
            String output = scope.stream()
                    .map(Enum::name)
                    .map(String::toLowerCase)
                    .collect(Collectors.joining(" "));
            out.value(output);
        }
    }

    @Override
    public List<Scope> read(JsonReader in) throws IOException {
        List<Scope> scopes = new ArrayList<>();
        for (String scope : in.nextString().split(" ")) {
            scopes.add(Scope.valueOf(scope.toUpperCase()));
        }
        return scopes;
    }
}
