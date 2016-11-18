package arend.arendvandormalen_pset3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Arend on 2016-11-15.
 * Adapter that puts the movie data in the right place.
 *
 */

class MovieAdapter extends ArrayAdapter<MovieData>{

    public MovieAdapter(Context context, ArrayList<MovieData> movies) {
        super(context, R.layout.single_item, movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View movieView = inflater.inflate(R.layout.single_item, parent, false);

        String movieTitle = getItem(position).getTitle();
        TextView movieTextView = (TextView) movieView.findViewById(R.id.textViewLine);
        movieTextView.setText(movieTitle);

        String movieYear = getItem(position).getYear();
        TextView yearTextView = (TextView) movieView.findViewById(R.id.yearLine);
        yearTextView.setText(movieYear);

        return  movieView;
    }

}