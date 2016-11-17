package arend.arendvandormalen_pset3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Arend on 2016-11-15.
 * Adapter that puts the movie data in the right place.
 *
 */

class MovieAdapter extends ArrayAdapter<String>{

    public MovieAdapter(Context context, String[] values) {
        super(context, R.layout.single_item, values);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View movieView = inflater.inflate(R.layout.single_item, parent, false);

        String movieTitle = getItem(position);

        TextView theTextView = (TextView) movieView.findViewById(R.id.textView1);

        theTextView.setText(movieTitle);

        return  movieView;

    }
}
