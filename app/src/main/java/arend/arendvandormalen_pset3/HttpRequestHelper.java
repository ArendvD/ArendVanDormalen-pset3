package arend.arendvandormalen_pset3;

import android.app.DownloadManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Arend on 2016-11-17.
 * This class helps to establish the internet connection
 * Returns JSON in String, based on input send to server.
 */
public class HttpRequestHelper {

    // Parts of url to give full plot
    private static final String url1 = "http://omdbapi.com/?";
    private static final String url2 = "=";
    private static final String url3 = "&y=&plot=full&r=json/";

    protected static synchronized String downloadFromServer(String type, String... params){

        String result = "";

        String chosenTag = params[0];

        String completeUrl = url1 + type + url2 + chosenTag + url3;

        URL url = null;
        try{
            url = new URL(completeUrl);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        HttpURLConnection connection;
        if(url != null){
            try{
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                Integer responseCode = connection.getResponseCode();

                if (200 <= responseCode && responseCode < 300){
                    BufferedReader br = new BufferedReader(new
                            InputStreamReader(connection.getInputStream()));
                    String line = br.readLine();
                    while (line != null){
                        result = result + line;
                        line = br.readLine();
                    }
                } else {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getErrorStream()));
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return result;

    }
}
