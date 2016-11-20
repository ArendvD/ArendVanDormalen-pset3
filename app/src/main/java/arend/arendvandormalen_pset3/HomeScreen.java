package arend.arendvandormalen_pset3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    //SharedPreferences sharedPreferences;
    //public static final String myPreferences = "myPrefs";

    String searchTerm;
    ArrayList<MovieData> searchResultList;
    ArrayList<String> searchResultTitles;
    MovieAsyncTask movieAsyncTask;
    DetailsAsyncTask detailsAsyncTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void sendToDatabase(View view) {

        EditText searchBox = (EditText) findViewById(R.id.movie_search_box);
        searchTerm = searchBox.getText().toString();

        movieAsyncTask = new MovieAsyncTask(HomeScreen.this);
        movieAsyncTask.execute(searchTerm);
    }

    public void parseResults(ArrayList<MovieData> searchResults){

        this.searchResultList = searchResults;

        // Enters ArrayList in adapter, which fills the ListView
        ListAdapter movieAdapter = new MovieAdapter(this, searchResults);
        ListView movieList = (ListView) findViewById(R.id.movie_list);
        movieList.setAdapter(movieAdapter);

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            // Retrieve MovieData related to clicked TextView
            MovieData selectedMovie = (MovieData) adapterView.getItemAtPosition(position);
            // Use Title from MovieData to find more extensive data
            String selectedTitle = selectedMovie.getTitle();

            Log.d("movieDetailsName", selectedTitle);

            detailsAsyncTask = new DetailsAsyncTask(HomeScreen.this, searchResultList);
            detailsAsyncTask.execute(selectedTitle);

            }
        });

    }

    public void switchToSingleMovie(MovieData movie){

        Intent goToDetailScreen = new Intent(this, ActivityDetailScreen.class);
        goToDetailScreen.putExtra("movie", movie);
        startActivity(goToDetailScreen);

    }
}
