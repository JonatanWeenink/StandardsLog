package nl.jonatanweenink.standardslog;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private StandardAdapter mAdapter;
    private Cursor mCursor;
    private RecyclerView mRecyclerView;
    private DataSource mDataSource;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

        private void updateUI() {
            mCursor =  mDataSource.findAll();
            if (mAdapter == null) {
                mAdapter = new StandardAdapter (mCursor);
                mRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.swapCursor(mCursor);
            }
        }
    private void updateStandard (Standard standard) {
        Intent intent = new Intent(this, StandardModifyActivity.class);
        intent.putExtra("standard", standard);
        intent.setAction(Intent.ACTION_EDIT);
        startActivity(intent);
    }
    private void addStandard() {
        Intent intent = new Intent(this, StandardModifyActivity.class);
        intent.setAction(Intent.ACTION_INSERT);
        startActivity(intent);
    }
    }

