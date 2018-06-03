package generisches.lab.trial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FirebaseAuthBasics extends AppCompatActivity {

    private static final String TAG = "FireBaseAuthBasics";
    private EditText email;
    private EditText password;
    private Button signin;
    private Button signup;
    private EditText nameforsignup;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_auths_basics);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        email = findViewById(R.id.firebase_email);
        password = findViewById(R.id.firebase_pwd);
        signin = findViewById(R.id.fb_signin);
        signup = findViewById(R.id.firebase_signup);
        nameforsignup = findViewById(R.id.txt_nameforsignup);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.w(TAG, "onAuthStateChanged:signed_in: " + user.getUid());
                    toastMessage("Successfully signed in with : " + user.getEmail());
                } else {
                    Log.w(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
            }
        };
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString();
                String pwd = password.getText().toString();
                if (!em.equals("") && !pwd.equals("")) {
                    mAuth.signInWithEmailAndPassword(em, pwd)
                            .addOnCompleteListener(FirebaseAuthBasics.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        Intent intent = new Intent(FirebaseAuthBasics.this, FBAfterLogin.class);
                                        intent.putExtra("mAuthname", mAuth.getCurrentUser().getDisplayName());
                                        intent.putExtra("mAuthID", mAuth.getCurrentUser().getUid());
                                        startActivity(intent);
                                    }
                                    else
                                        toastMessage("Try Again");
                                }
                            });
                } else {
                    toastMessage("Fill in all fields.");
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String em = email.getText().toString();
                final String pwd = password.getText().toString();
                if(em!=null && pwd != null && nameforsignup != null) {
                    final HashMap<String, String> obj = new HashMap<>();
                    obj.put("Name", nameforsignup.getText().toString());
                    obj.put("Email", em);
                    mAuth.createUserWithEmailAndPassword(em, pwd)
                            .addOnCompleteListener(FirebaseAuthBasics.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user != null) {
                                            UserProfileChangeRequest profileUpdate
                                                    = new UserProfileChangeRequest.Builder().setDisplayName(nameforsignup.getText().toString()).build();
                                            user.updateProfile(profileUpdate);
                                            mDatabaseReference.child("Users").child(user.getUid().toString()).setValue(obj)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()){
                                                                toastMessage("Added to DB");
                                                                email.setText("");
                                                                password.setText("");
                                                                nameforsignup.setText("");
                                                            }
                                                            else{
                                                                toastMessage("Could not add to DB");
                                                            }
                                                        }
                                                    });

                                        }
                                        mAuth.signOut();
                                        toastMessage("Signed up successfully. Pls login");
                                    } else {
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        toastMessage("Sign Up failed");
                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthWeakPasswordException e) {
                                            //pwd.setError(getString(R.string.error_weak_password));
                                            //pwd.requestFocus();
                                            toastMessage("Weak Password");
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            //em.setError(getString(R.string.error_invalid_email));
                                            //em.requestFocus();
                                            toastMessage("Invalid Email");
                                        } catch (FirebaseAuthUserCollisionException e) {
                                            //em.setError(getString(R.string.error_user_exists));
                                            //em.requestFocus();
                                            toastMessage("User already present");
                                        } catch (Exception e) {
                                            Log.e(TAG, e.getMessage());
                                        }
                                    }
                                }
                            });
                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void toastMessage(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
