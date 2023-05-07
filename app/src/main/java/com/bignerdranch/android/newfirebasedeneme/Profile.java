package com.bignerdranch.android.newfirebasedeneme;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.newfirebasedeneme.databinding.ActivityPortalBinding;
import com.bignerdranch.android.newfirebasedeneme.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class Profile extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private ActivityProfileBinding binding;
    DocumentReference docRef;
    FirebaseUser currentUser;


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.profile_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.profile_upload) {
            Intent intentToCreateProfile = new Intent(Profile.this, CreateProfile.class);
            startActivity(intentToCreateProfile);
            finish();
        }
        else if (item.getItemId()==R.id.portal){
            Intent intentToPortal = new Intent(Profile.this,Portal.class);
            startActivity(intentToPortal);
            finish();
        }
        else if(item.getItemId()==R.id.news){
            Intent intentToNews = new Intent(Profile.this,News.class);
            startActivity(intentToNews);
            finish();
        }

        else if (item.getItemId() == R.id.signout) {
            firebaseAuth.signOut();
            Intent intentMain = new Intent(Profile.this, MainActivity.class);
            startActivity(intentMain);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        docRef = firebaseFirestore.collection("users").document(currentUser.getUid());
        getDataFromDatabase();

    }







    public void getDataFromDatabase() {

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Map<String, Object> data = document.getData();

                    String name = (String) data.get("name");
                    String surname = (String) data.get("surname");
                    String email = (String) data.get("email");
                    String gender = (String) data.get("gender");
                    String graduateYear = (String) data.get("graduateYear");
                    String password = (String) data.get("password");
                    String photo = (String) data.get("photo");
                    String Country = (String) data.get("country");
                    String JobInfos = (String) data.get("JobInfos");
                    String socialMediaInfos = (String) data.get("socialMediaInfos");
                    String phoneNumber = (String) data.get("phoneNumber");
                    String secondEmail = (String) data.get("secondemail");

                    binding.ad.setText(binding.ad.getText()+" "+name);
                    binding.soyad.setText(binding.soyad.getText()+" " +surname);
                    binding.cinsiyet.setText(binding.cinsiyet.getText()+" "+gender);
                    binding.graduate.setText(binding.graduate.getText()+" "+graduateYear);
                    binding.parola.setText(binding.parola.getText()+" "+password);
                    binding.country.setText(binding.country.getText()+" "+Country);
                    binding.jobinfos.setText(binding.jobinfos.getText()+" "+JobInfos);
                    binding.socialmedia.setText(binding.socialmedia.getText()+" "+socialMediaInfos);
                    binding.phone.setText(binding.phone.getText()+" "+phoneNumber);
                    binding.email.setText(binding.email.getText()+" "+email);
                    binding.secondemail.setText(binding.secondemail.getText()+" "+secondEmail);
                    Picasso.get().load(photo).into(binding.fotograf);
                } else {
                    Log.d(TAG, "Belirtilen doküman bulunamadı!");
                }
            } else {
                Log.d(TAG, "Doküman alınırken hata oluştu: ", task.getException());
            }
        });

                    }


                }

