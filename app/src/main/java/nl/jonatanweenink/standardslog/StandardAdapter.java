package nl.jonatanweenink.standardslog;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/*
 *Created by Laptop Weenink on 5-3-2018.
 */
public class StandardAdapter extends RecyclerView.Adapter<StandardAdapter.StandardViewHolder> {
    private Cursor cursor;
    public StandardAdapter( Cursor cursor) {
        this.cursor = cursor;
    }
    @Override
    public StandardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate our item_game layout
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_standard, parent, false);
        // Instantiate a GameViewHolder and pass our layout as it's view
        return new StandardViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(StandardViewHolder holder, int position) {
        // Move the cursor to the right position
        cursor.moveToPosition(position);
        // Create a game object from the cursor's data
        Standard standard= new Standard();
        standard.setId(cursor.getInt(cursor.getColumnIndex(StandardContract.StandardEntry.COLUMN_NAME_ID)));
        standard.setTitle(cursor.getString(cursor.getColumnIndex(StandardContract.StandardEntry.COLUMN_NAME_TITLE)));
        standard.setArtist(cursor.getString(cursor.getColumnIndex(StandardContract.StandardEntry.COLUMN_NAME_ARTIST)));
        standard.setDateAdded(cursor.getString(cursor.getColumnIndex(StandardContract.StandardEntry.COLUMN_NAME_DATE)));
        standard.setStatus(cursor.getString(cursor.getColumnIndex(StandardContract.StandardEntry.COLUMN_NAME_STATUS)));
        standard.setNotes(cursor.getString(cursor.getColumnIndex(StandardContract.StandardEntry.COLUMN_NAME_NOTES)));
        standard.setKey(cursor.getString(cursor.getColumnIndex(StandardContract.StandardEntry.COLUMN_NAME_KEY)));
        // Bind the game object to the view
        holder.bind(standard);
    }
    @Override
    public int getItemCount() {
        return (cursor == null ? 0 : cursor.getCount());
    }
    public void swapCursor(Cursor newCursor) {
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
    /**
     * A wrapper class representing a single view or row within our RecyclerView. The ViewHolder
     * holds a reference to all the views and the game object.
     */
    class StandardViewHolder extends RecyclerView.ViewHolder {
        private Standard standard;
        private final TextView title;
        private final TextView artist;
        private final TextView status;
        private final TextView date;
        StandardViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.standardTitle);
            date = view.findViewById(R.id.standardDate);
            status = view.findViewById(R.id.standardStatus);
            artist = view.findViewById(R.id.standardArtist);
        }
        public Standard getStandard() {
            return standard;
        }
        void bind(final Standard standard) {
            this.standard = standard;
            title.setText(standard.getTitle());
            date.setText(standard.getDateAdded());
            status.setText(standard.getStatus());
            artist.setText(standard.getArtist());
        }
    }
}
