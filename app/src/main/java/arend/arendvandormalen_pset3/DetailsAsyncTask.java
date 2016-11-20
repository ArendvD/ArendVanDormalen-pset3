package arend.arendvandormalen_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Arend on 2016-11-17.
 * AsyncTask for managing loading of a single movie data. Keeps main thread working.
 *
 */

public class DetailsAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    HomeScreen activity;
    String searchType = "t";
    String movieName;
    String movieURL;

    ArrayList<MovieData> movieDataArrayList;

    public DetailsAsyncTask(HomeScreen activity, ArrayList<MovieData> movieDataArrayList){
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
        this.movieDataArrayList = movieDataArrayList;
    }

    // onPreExecute()
    @Override
    protected void onPreExecute(){
        Toast.makeText(context, "Getting data from the server", Toast.LENGTH_SHORT).show();
    }

    // doInBackground()
    @Override
    protected String doInBackground(String... params) {
        this.movieName = params[0];
        return HttpRequestHelper.downloadFromServer(searchType, params);
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

                // Loops through list of search results to find selected movie
                for (int i = 0; i < movieDataArrayList.size(); i++) {
                    MovieData currentMovie = movieDataArrayList.get(i);
                    String currentTitle = currentMovie.getTitle();
                    if (currentTitle.equals(movieName)){

                        Log.d("Same movie found:", movieName);

                        // add elaborate data
                        String actors = resultsObj.getString("Actors");
                        currentMovie.setActors(actors);
                        String plot = resultsObj.getString("Plot");
                        currentMovie.setPlot(plot);
                        String director = resultsObj.getString("Director");
                        currentMovie.setDirector(director);
                        String runtime = resultsObj.getString("Runtime");
                        currentMovie.setRuntime(runtime);

                        activity.switchToSingleMovie(currentMovie);
                        break;

                    }

                }

            }

        } catch (JSONException e){
            e.printStackTrace();
        }

    }

}
