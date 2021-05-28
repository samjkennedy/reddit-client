package com.skennedy.reddit.client.common.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.skennedy.reddit.client.common.model.OAuthScope;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScopeListTypeAdapter extends TypeAdapter<List<OAuthScope>> {

    @Override
    public void write(JsonWriter out, List<OAuthScope> OAuthScope) throws IOException {
        if (OAuthScope == null) {
            out.nullValue();
        } else {
            String output = OAuthScope.stream()
                    .map(Enum::name)
                    .map(String::toLowerCase)
                    .collect(Collectors.joining(" "));
            out.value(output);
        }
    }

    @Override
    public List<OAuthScope> read(JsonReader in) throws IOException {
        List<OAuthScope> OAuthScopes = new ArrayList<>();
        for (String scope : in.nextString().split(" ")) {
            OAuthScopes.add(OAuthScope.valueOf(scope.toUpperCase()));
        }
        return OAuthScopes;
    }
}
