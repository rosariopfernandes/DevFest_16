package io.github.rosariopfernandes.devfest16;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

public class AuthActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN=9001;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent auth = AuthUI.getInstance().createSignInIntentBuilder()
                        .setLogo(R.drawable.gdg)
                        .setTheme(R.style.AuthTheme)
                        .setProviders(
                                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER)
                                        .build()))
                        .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                        .build();
                startActivityForResult(auth, RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN)
        {
            if(resultCode == RESULT_OK)
                startActivity(new Intent(AuthActivity.this, MainActivity.class));
            else
                Toast.makeText(AuthActivity.this, getString(R.string.toast_error),
                        Toast.LENGTH_SHORT).show();
        }
    }
}