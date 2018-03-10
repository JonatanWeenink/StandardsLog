package nl.jonatanweenink.standardslog;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.Objects;

public class StandardModifyActivity extends AppCompatActivity {


    private Standard standard;
    private ArrayAdapter statusAdapter;
    private TextInputEditText titleInput;
    private TextInputEditText artistInput;
    private TextInputEditText notesInput;
    private TextInputEditText keyInput;
    private Spinner statusSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_modify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find all our views from within our layout
        titleInput = findViewById(R.id.inputStandardTitle);
        artistInput = findViewById(R.id.inputStandardArtist);
        statusSpinner = findViewById(R.id.spinnerStandardStatus);
        notesInput = findViewById(R.id.inputStandardNotes);
        keyInput = findViewById(R.id.inputStandardKey);
        // Create an ArrayAdapter using the string array and a default spinner layout
        statusAdapter = ArrayAdapter.createFromResource(this, R.array.standard_status,
                R.layout.support_simple_spinner_dropdown_item);
        // Set the adapter to the spinner
        statusSpinner.setAdapter(statusAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        // Based on the action we will add or update a game
        if (Objects.equals(getIntent().getAction(), Intent.ACTION_INSERT)) {
            // We are adding a new game
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveStandard();
                }
            });
        } else {
            // We are updating an existing game so start by retrieving it from the intent
            standard = (Standard) getIntent().getSerializableExtra("standard");
            // Set the values for the views
            titleInput.setText(standard.getTitle());
            artistInput.setText(standard.getArtist());
            notesInput.setText(standard.getNotes());
            keyInput.setText(standard.getKey());
            // Get the position of the game's status within the adapter
            int spinnerPosition = statusAdapter.getPosition(standard.getStatus());
            statusSpinner.setSelection(spinnerPosition);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateStandard();
                }
            });
        }
    }


    public void saveStandard() {
        String title = titleInput.getText().toString();
        String artist = artistInput.getText().toString();
        String notes = notesInput.getText().toString();
        String key = keyInput.getText().toString();
        String standardStatus = statusSpinner.getSelectedItem().toString();

        if (title.isEmpty()) {
            titleInput.setError(getString(R.string.error_standard_add_title_required));
        } else if (artist.isEmpty()) {
            artistInput.setError(getString(R.string.error_standard_add_artist_required));
        } else {
            Standard standard = new Standard(title, artist, standardStatus, notes, key);

            DataSource dataSource = new DataSource(this);
            dataSource.open();
            dataSource.save(standard);

            Toast.makeText(this, R.string.message_standard_saved, Toast.LENGTH_LONG).show();

            finish();
        }
    }

    private void updateStandard() {
        // Get the input from the Views
        String title = titleInput.getText().toString();
        String artist = artistInput.getText().toString();
        String status = statusSpinner.getSelectedItem().toString();
        String notes = notesInput.getText().toString();
        String key = keyInput.getText().toString();

        // Validate that the title and platform is not empty
        if (title.isEmpty()) {
            titleInput.setError(getString(R.string.error_standard_add_title_required));
        } else if (artist.isEmpty()) {
            artistInput.setError(getString(R.string.error_standard_add_artist_required));
        } else {
            Standard updatedStandard = new Standard(title, artist, status, notes, key);

            DataSource dataSource = new DataSource(this);
            dataSource.open();
            dataSource.update(standard.getId(), updatedStandard); 

            Toast.makeText(this, R.string.message_standard_modified, Toast.LENGTH_LONG).show();

            finish();
        }
    }



}