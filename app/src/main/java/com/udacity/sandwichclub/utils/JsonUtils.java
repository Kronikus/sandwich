package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class JsonUtils {

    private static List<String> getStringList(JSONArray jsonArray) {
        List<String> stringArray = new ArrayList<String>();
        if (jsonArray != null) {
            int length = jsonArray.length();
            //  stringArray =

            for (int i = 0; i < length; i++) {
                stringArray.add(jsonArray.optString(i));
            }
        }
        return stringArray;
    }

    public static Sandwich parseSandwichJson(String json) {

        Sandwich result = null;
        if (json != null) {
            try {
                JSONObject c = new JSONObject(json);
                JSONObject b = new JSONObject(String.valueOf(c.getJSONObject("name")));
                String mainName = b.getString("mainName");
                List<String> alsoKnownAs = (List<String>) getStringList(b.getJSONArray("alsoKnownAs"));
                String placeOfOrigin = c.getString("placeOfOrigin");
                String description = c.getString("description");
                String image = c.getString("image");
                List<String> ingredients = getStringList(c.getJSONArray("ingredients"));
                result = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }

        } else {
            Log.e(TAG, "Couldn't get json from server.");

        }

        return result;
    }
}
