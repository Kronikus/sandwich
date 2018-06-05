package com.udacity.sandwichclub.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.udacity.sandwichclub.MainActivity;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;


public class JsonUtils extends MainActivity{

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

    public static Sandwich parseSandwichJson(Context context,String json) {

        Sandwich result = null;
        if (json != null) {
            try {
                JSONObject c = new JSONObject(json);
                JSONObject b = new JSONObject(String.valueOf(c.getJSONObject(context.getString(R.string.sandwich_name))));
                String mainName = b.getString(  context.getString(R.string.sandwich_mainname));
                List<String> alsoKnownAs = (List<String>) getStringList(b.getJSONArray(  context.getString(R.string.sandwich_alsoknownas)));
                String placeOfOrigin = c.getString(  context.getString(R.string.sandwich_placeoforigin));
                String description = c.getString(  context.getString(R.string.sandwich_description));
                String image = c.getString(  context.getString(R.string.sandwich_image));
                List<String> ingredients = getStringList(c.getJSONArray( context.getString(R.string.sandwich_ingredients)));
                result = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }

        } else {
            Log.e(TAG, "Couldn't get json.");

        }

        return result;
    }


}
