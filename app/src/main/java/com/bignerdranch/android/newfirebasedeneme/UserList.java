package com.bignerdranch.android.newfirebasedeneme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bignerdranch.android.newfirebasedeneme.databinding.ActivityUserListBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.Map;

public class UserList extends AppCompatActivity {



    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private ActivityUserListBinding activityUserListBinding ;

    ArrayList<userModel> userModelList;
    UserAdapter userAdapter;



    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.user_list_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }






    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.profile) {
            Intent intentToCreateProfile = new Intent(UserList.this, Profile.class);
            startActivity(intentToCreateProfile);
            finish();
        }
        else if (item.getItemId()==R.id.portal){
            Intent intentToPortal = new Intent(UserList.this,Portal.class);
            startActivity(intentToPortal);
            finish();
        }
        else if(item.getItemId()==R.id.news){
            Intent intentToNews = new Intent(UserList.this,News.class);
            startActivity(intentToNews);
            finish();
        }

        else if (item.getItemId() == R.id.signout) {
            firebaseAuth.signOut();
            Intent intentMain = new Intent(UserList.this, MainActivity.class);
            startActivity(intentMain);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUserListBinding = ActivityUserListBinding.inflate(getLayoutInflater());
        View view = activityUserListBinding.getRoot();
        setContentView(view);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getDataFromDatabase();
    }





    public void getDataFromDatabase() {

        CollectionReference collectionReference = firebaseFirestore.collection("Kullanıcılar");

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Toast.makeText(UserList.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if (queryDocumentSnapshots != null) {

                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {

                        Map<String, Object> data = documentSnapshot.getData();

                        String name = (String) data.get("name");
                        String surname = (String) data.get("surname");
                        String email = (String) data.get("email");
                        String photo = (String) data.get("photo");


                        userModel user = new userModel(name, surname, email, photo);

                        userModelList.add(user);


                    }
                    userAdapter.notifyDataSetChanged();


                }

            }
        });


    }


}