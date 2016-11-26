package io.github.rosariopfernandes.devfest16;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        txtName = (TextView) findViewById(R.id.txtName);
        txtName.setText(user.getDisplayName());

        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtEmail.setText(user.getEmail());

        /*fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });*/
    }

}
