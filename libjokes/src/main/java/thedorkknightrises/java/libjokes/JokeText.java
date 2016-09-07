package thedorkknightrises.java.libjokes;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class JokeText {
    public String getJokeText() {
        final String BASE_URL = "http://api.icndb.com/jokes/random?exclude=[explicit]";
        try {
            URL url = new URL(BASE_URL);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp;
            strTemp = br.readLine();
            JSONObject jsonObject = new JSONObject(strTemp);
            return Jsoup.parse(jsonObject.getJSONObject("value").getString("joke")).text();
        } catch (IOException e) {
            e.printStackTrace();
            return "Quick! Laugh and pretend like this is a really funny joke!!";
        }
    }
}
