package com.project.cryptonews.ui.news.viewmodel;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.project.cryptonews.R;
import com.project.cryptonews.data.eventregistry.ERResponse;
import com.project.cryptonews.data.eventregistry.Result;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

/**
 * Created by Venkat on 03/04/18.
 */
public class EventRegistryTypeAdapterTest  {

    Context context;
    EventRegistryTypeAdapter typeAdapter;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
        typeAdapter = new EventRegistryTypeAdapter();
    }


    @Test
    public void deserialize() throws Exception {
        InputStream is = context.getResources().openRawResource(R.raw.article);
        Writer writer = new StringWriter();
        Reader reader = new BufferedReader(new InputStreamReader(is));
        char[] buffer = new char[1024];
        try {
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }
        String jsonString = writer.toString();
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(jsonString);
        List<Result> results = typeAdapter.deserialize(element, new TypeToken<ERResponse>(){}.getType(), null);
        Assert.assertTrue(results.isEmpty());
    }
}