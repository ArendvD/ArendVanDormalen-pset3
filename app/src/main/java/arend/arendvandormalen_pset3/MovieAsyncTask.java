package arend.arendvandormalen_pset3;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Arend on 2016-11-17.
 *
 *
 */

public class MovieAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    Activity activity;

    public MovieAsyncTask(Activity activity){
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

        if(result.length()==0){ //TODO: fix error check
            Toast.makeText(context, "No data was found", Toast.LENGTH_SHORT).show();
        } else {

            ArrayList<MovieData> movieDataArrayList = new ArrayList<>();
            try{
                JSONObject resultsObj = new JSONObject(result);
                JSONArray movies = resultsObj.getJSONArray("Search");
                for (int i = 0; i < movies.length(); i++) {
                    JSONObject movie = movies.getJSONObject(i);
                    String title = movie.getString("Title");
                    String year = movie.getString("Year");
                    String imageLink = movie.getString("Poster");
                    MovieData movieData = new MovieData(title, year, imageLink);
                    movieDataArrayList.add(movieData);
                }
                String message = "First title is" + movieDataArrayList.get(0).getTitle();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();

            } catch (JSONException e){
                e.printStackTrace();
            }
        }

    }
}
