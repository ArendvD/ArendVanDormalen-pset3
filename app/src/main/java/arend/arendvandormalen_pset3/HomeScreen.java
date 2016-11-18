package arend.arendvandormalen_pset3;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    //SharedPreferences sharedPreferences;
    //public static final String myPreferences = "myPrefs";

    String searchTerm;
    ArrayList<String> searchResultTitles;
    MovieAsyncTask movieAsyncTask;

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

        searchResultTitles = new ArrayList<>();

        for (int i = 0; i < searchResults.size(); i++) {
            String movieTitle = searchResults.get(i).getTitle();
            searchResultTitles.add(movieTitle);
        }

        ListAdapter movieAdapter = new MovieAdapter(this, searchResultTitles);

        ListView movieList = (ListView) findViewById(R.id.movie_list);
        movieList.setAdapter(movieAdapter);

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                /*
                String testURL = "http://www.omdbapi.com/?t=" + "frozen";
                try {
                    JsonReader movieReader = new JsonReader();

                } catch(ParseException e){
                    e.printStackTrace();
                }
                */
                searchTerm = "cop"; //Test

                movieAsyncTask = new MovieAsyncTask(HomeScreen.this);
                movieAsyncTask.execute(searchTerm);

                String moviePicked = "You selected " +
                        String.valueOf(adapterView.getItemAtPosition(position));

                Toast.makeText(HomeScreen.this, moviePicked, Toast.LENGTH_SHORT).show();

            }
        });



    }
}
