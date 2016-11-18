package arend.arendvandormalen_pset3;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Arend on 2016-11-17.
 * AsyncTask for managing loading of movie data. Keeps main thread working.
 *
 */

public class MovieAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    HomeScreen activity;

    ArrayList<MovieData> movieDataArrayList;

    public MovieAsyncTask(HomeScreen activity){
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    // onPreExecute()
    @Override
    protected void onPreExecute(){
        Toast.makeText(context, "Getting data from the server", Toast.LENGTH_SHORT).show();
    }

    // doInBackground()
    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    // onProgressUpdate()
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


    // onPostExecute()
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try{
            JSONObject resultsObj = new JSONObject(result);
            String response = resultsObj.getString("Response");
            if (response.equals("False")){
                Toast.makeText(context, "No movies were found", Toast.LENGTH_SHORT).show();
            } else {

                JSONArray movies = resultsObj.getJSONArray("Search");
                movieDataArrayList = new ArrayList<>();
                for (int i = 0; i < movies.length(); i++) {
                    JSONObject movie = movies.getJSONObject(i);
                    String title = movie.getString("Title");
                    String year = movie.getString("Year");
                    String imageLink = movie.getString("Poster");
                    MovieData movieData = new MovieData(title, year, imageLink);
                    movieDataArrayList.add(movieData);
                }

                Log.d("Eerste Title", movieDataArrayList.get(0).getTitle());

                activity.parseResults(movieDataArrayList);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }

    }

}
