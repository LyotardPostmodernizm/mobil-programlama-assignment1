package com.bignerdranch.android.newfirebasedeneme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.squareup.picasso.Picasso;

public class CreateProfile extends AppCompatActivity {


    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    DocumentReference documentReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
;
    EditText iname;
    EditText isurname;
    RadioButton erkek;
    RadioButton kız;
    EditText iemail;
    EditText iphone;
    EditText isecondemail;
    EditText igraduateYear;
    ImageView imageView;
    EditText ijobinfos;
    EditText isocailMedia;

    EditText icountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        documentReference= db.collection("users").document(user.getUid());

        iname = findViewById(R.id.et_name);
        isurname = findViewById(R.id.et_sirname);
        erkek = findViewById(R.id.erkekButton);
        kız = findViewById(R.id.kızButton);
        iemail = findViewById(R.id.et_email);
        iphone = findViewById(R.id.etphone);
        isecondemail = findViewById(R.id.et_secondemail);
        igraduateYear = findViewById(R.id.et_graduateYear);
        imageView = findViewById(R.id.sendButton);
        ijobinfos = findViewById(R.id.jobinfos);
        isocailMedia = findViewById(R.id.social1);
        icountry = findViewById(R.id.country);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();





        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String name = null;
                String surname = null;
                String gender = null;
                String graduationYear = null;
                String country = null;
                String email = null;
                String phone = null;
                String secondEmail = null;
                String photo = null;
                String jobInfos = null;
                String socialMediaInfos = null;


                if (task.getResult().exists()) {
                    name = task.getResult().getString("name");
                    surname = task.getResult().getString("surname");
                    gender = task.getResult().getString("gender");
                    graduationYear = task.getResult().getString("graduateYear");
                    country = task.getResult().getString("country");
                    jobInfos = task.getResult().getString("JobInfos");
                    socialMediaInfos = task.getResult().getString("socialMediaInfos");
                    phone = task.getResult().getString("phoneNumber");
                    email = task.getResult().getString("email");
                    secondEmail = task.getResult().getString("secondemail");
                    photo = task.getResult().getString("photo");

                } else {
                    Toast.makeText(CreateProfile.this, "Profil Bulunamadı", Toast.LENGTH_SHORT);
                }


                iname.setText(name);
                isurname.setText(surname);
                if (gender == "erkek")
                    erkek.toggle();
                else if (gender == "kız")
                    kız.toggle();

                igraduateYear.setText(graduationYear);
                icountry.setText(country);

                iemail.setText(email);
                iphone.setText(phone);
                isecondemail.setText(secondEmail);
                Picasso.get().load(photo).into(imageView);
                ijobinfos.setText(jobInfos);
                isocailMedia.setText(socialMediaInfos);

            }
        });
    }

    private void updateProfile() {
        String gender="";
        final String name = iname.getText().toString();
        final String surname = isurname.getText().toString();
        if(erkek.isChecked()){
            gender = "erkek";
        }
        else if(kız.isChecked()){
            gender = "kız";
        }
        final String email = iemail.getText().toString();
        final String phone = iphone.getText().toString();
        final String secondemail = isecondemail.getText().toString();
        final String graduateYear = igraduateYear.getText().toString();
        final String photo = imageView.getResources().toString();
        final String jobinfos = ijobinfos.getText().toString();
        final String socialMedia = isocailMedia.getText().toString();
        final String country = icountry.getText().toString();


        final DocumentReference sfDocRef = db.collection("users").document(user.getUid());

        String finalGender = gender;
        db.runTransaction(new Transaction.Function<Void>() {
                    @Override
                    public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                        DocumentSnapshot snapshot = transaction.get(sfDocRef);


                        transaction.update(sfDocRef, "name",name);
                        transaction.update(sfDocRef, "surname",surname);
                        transaction.update(sfDocRef, "gender", finalGender);
                        transaction.update(sfDocRef, "email",email);
                        transaction.update(sfDocRef, "graduateYear",graduateYear);
                        transaction.update(sfDocRef, "photo",photo);
                        transaction.update(sfDocRef, "country",country);
                        transaction.update(sfDocRef, "JobInfos",jobinfos);
                        transaction.update(sfDocRef, "socialMediaInfos",socialMedia);
                        transaction.update(sfDocRef, "phoneNumber",phone);
                        transaction.update(sfDocRef, "secondemail",secondemail);



                        // Success1
                        return null;
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CreateProfile.this,"Profil Güncellendi.",Toast.LENGTH_SHORT);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CreateProfile.this," Profil Güncelleme Başarısız!",Toast.LENGTH_SHORT);

                    }
                });


    }





}