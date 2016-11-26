package io.github.rosariopfernandes.devfest16;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private ListView listView;
    private AlertDialog.Builder alertDialog;
    private EditText txtSessionTime, txtSessionTitle;
    private DatabaseReference mRootRef;
    private FirebaseListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRootRef = FirebaseDatabase.getInstance().getReference().child("sessions");
        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new FirebaseListAdapter<Session>(this, Session.class,
                android.R.layout.two_line_list_item, mRootRef) {
            @Override
            protected void populateView(View view, Session session, int position) {
                ((TextView)view.findViewById(android.R.id.text1)).setText(session.getStartTime()+"");
                ((TextView)view.findViewById(android.R.id.text2)).setText(session.getSessionName());
            }
        };
        listView.setAdapter(mAdapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_session, null);
                txtSessionTime = (EditText) dialogView.findViewById(R.id.txtSessionTime);
                txtSessionTitle = (EditText) dialogView.findViewById(R.id.txtSessionName);
                alertDialog = new AlertDialog.Builder(MainActivity.this,
                        android.R.style.Theme_Material_Dialog);
                alertDialog.setTitle(R.string.newSession);
                alertDialog.setCancelable(false);
                alertDialog.setView(dialogView);
                alertDialog.setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setPositiveButton(R.string.action_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Session newSession = new Session(txtSessionTime.getText().toString(),
                                txtSessionTitle.getText().toString(),
                                "1234");
                        mRootRef.push().setValue(newSession);
                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_profile)
        {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
