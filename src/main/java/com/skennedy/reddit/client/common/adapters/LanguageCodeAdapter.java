package com.skennedy.reddit.client.common.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.skennedy.reddit.client.common.model.LanguageCode;

import java.io.IOException;

public class LanguageCodeAdapter extends TypeAdapter<LanguageCode> {
    @Override
    public void write(JsonWriter out, LanguageCode value) throws IOException {
        if(value != null) {
            out.value(value.name().toLowerCase());
        } else {
            out.nullValue();
        }
    }

    @Override
    public LanguageCode read(JsonReader in) throws IOException {
        return LanguageCode.valueOf(in.nextString().toUpperCase());
    }
}
