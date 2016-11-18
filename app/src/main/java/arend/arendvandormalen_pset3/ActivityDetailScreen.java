package arend.arendvandormalen_pset3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Arend on 2016-11-18.
 * Activity for layout showing single item.
 */

public class ActivityDetailScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_screen);

        // Read data from MovieData object and assign in xml.
        Intent i = getIntent();
        MovieData movie = (MovieData) i.getSerializableExtra("movie");

        String title = movie.getTitle();
        TextView titleView = (TextView)findViewById(R.id.movie_title);
        titleView.setText(title);

        String year = movie.getYear();
        TextView yearView = (TextView)findViewById(R.id.movie_year);
        yearView.setText(year);

        String actors = movie.getActors();
        TextView actorsView = (TextView)findViewById(R.id.movie_actors);
        actorsView.setText(actors);

        String director = movie.getDirector();
        TextView directorView = (TextView)findViewById(R.id.movie_director);
        directorView.setText(director);

        String plot = movie.getPlot();
        TextView plotView = (TextView)findViewById(R.id.movie_plot);
        plotView.setText(plot);

        String runtime = movie.getRuntime();
        TextView runtimeView = (TextView)findViewById(R.id.movie_runtime);
        runtimeView.setText(runtime);

    }



}
