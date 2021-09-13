package com.example.myisamlicapplication.data.azkarProvider;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.myisamlicapplication.data.pojo.Zekr;
import com.example.myisamlicapplication.data.pojo.ZekrType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AzkarProvider {

    private static ArrayList<Zekr> getAllAzkar(Context context) {
        try {
            InputStream azkarFile = context.getAssets().open("azkar/azkar.json");
            int size = azkarFile.available();
            byte[] bytes = new byte[size];
            azkarFile.read(bytes);
            azkarFile.close();
            String azkarString = new String(bytes, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            ArrayList<Zekr> azkar = gson.fromJson(azkarString, new TypeToken<List<Zekr>>() {
            }.getType());
            return azkar;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public  static  ArrayList<Zekr> getAzkarByType(Context context,@NonNull String zekrType){
        return  getAllAzkar(context)
                .stream()
                .filter(zekr -> zekrType.equals(zekr.getCategory()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static HashSet<ZekrType> getAzkarTypes(Context context){
      return getAllAzkar(context)
                .stream()
                .map(zekr -> new ZekrType(zekr.getCategory()))
                .collect(Collectors.toCollection(HashSet::new));
    }
}