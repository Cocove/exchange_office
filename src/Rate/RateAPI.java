package Rate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RateAPI {
    public static String getHuobi1() {
        return huobi1;
    }

    public static void setHuobi1(String huobi1) {
        RateAPI.huobi1 = huobi1;
    }

    public static String getHuobi2() {
        return huobi2;
    }

    public static void setHuobi2(String huobi2) {
        RateAPI.huobi2 = huobi2;
    }

    public static String getHuilv() throws IOException {
        String url_str=("https://v6.exchangerate-api.com/v6/238e764b1a0052b725907364/pair/"+huobi1+"/"+huobi2);
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

// Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

// Accessing object
        String req_result = jsonobj.get("conversion_rate").getAsString();
        huilv = req_result;
        return huilv;
    }

    private static String huobi1  = "USD";
    private static String huobi2 = "USD";
    private static String huilv;
}
