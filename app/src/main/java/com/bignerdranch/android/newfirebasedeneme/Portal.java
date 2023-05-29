package com.bignerdranch.android.newfirebasedeneme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bignerdranch.android.newfirebasedeneme.databinding.ActivityPortalBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Portal extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private ActivityPortalBinding binding;
    ArrayList<Post> postList;
    UploadAdapter uploadAdapter;

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.portal_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_post) {
            Intent intentToUpload = new Intent(Portal.this, Upload.class);
            startActivity(intentToUpload);
        }
        else if (item.getItemId()==R.id.profile){
            Intent intentToProfile = new Intent(Portal.this,Profile.class);
            startActivity(intentToProfile);
            finish();
        }
        else if(item.getItemId()==R.id.news){
            Intent intentToNews = new Intent(Portal.this,News.class);
            startActivity(intentToNews);
            finish();
        }
        else if(item.getItemId()==R.id.map){
            Intent intentToMap = new Intent(Portal.this,MainMapsActivity.class);
            startActivity(intentToMap);
            finish();
        }

        else if (item.getItemId() == R.id.signout) {
            firebaseAuth.signOut();
            Intent intentMain = new Intent(Portal.this, MainActivity.class);
            startActivity(intentMain);
            finish();
        }



        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPortalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        postList = new ArrayList<Post>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getDataFromDatabase();




        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadAdapter = new UploadAdapter(postList);
        binding.recyclerView.setAdapter(uploadAdapter);

    }





    public void getDataFromDatabase() {

        CollectionReference collectionReference = firebaseFirestore.collection("Yüklemeler");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Toast.makeText(Portal.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if (queryDocumentSnapshots != null) {

                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {

                        Map<String,Object> data = documentSnapshot.getData();

                        String name = (String) data.get("name");
                        String surname = (String) data.get("surname");
                        String email = (String) data.get("email");
                        String phoneNumber = (String)data.get("phoneNumber");
                        String comment = (String) data.get("comment");
                        String image = (String) data.get("image");

                        Post post = new Post(name,surname,email,phoneNumber,comment,image);

                        postList.add(post);

                    }
                    uploadAdapter.notifyDataSetChanged();

                }

            }
        });


    }

}