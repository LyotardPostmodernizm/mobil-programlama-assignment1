package com.bignerdranch.android.newfirebasedeneme;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.newfirebasedeneme.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
//import com.bignerdranch.android.newfirebasedeneme.databinding.ActivitySignupBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private ActivityMainBinding binding;
    DocumentReference documentReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String currentuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        user = FirebaseAuth.getInstance().getCurrentUser();

        firebaseAuth = FirebaseAuth.getInstance();



       if (user != null) {

            Intent intent = new Intent(MainActivity.this, Portal.class);
           startActivity(intent);
            finish();

       }



    }

    public void signInClicked (View view) {

        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        if(email.equals("") || password.equals("")){
            Toast.makeText(this,"Lütfen e-mailinizi ve/veya parolanızı giriniz",Toast.LENGTH_LONG);
        }


        else{
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    if(email=="admin@gmail.com" && password == "adminadmin"){
                        Toast.makeText(MainActivity.this, "Hoş geldiniz admin. Kullanıcı listesine yönlendiriliyorsunuz.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,UserList.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        

                        Toast.makeText(MainActivity.this, "Giriş Yapıldı! Yıldız Portal'a Yönlendiriliyorsunuz.", Toast.LENGTH_LONG);
                        Intent intent = new Intent(MainActivity.this, Portal.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
            });
        }




    }

    public void signUpClicked (View view) {

        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        User usern = new User("-","-","-",user.getEmail(),"-",password,"https://firebasestorage.googleapis.com/v0/b/newfirebasedeneme.appspot.com/o/images%2Fuser_photo.jpg?alt=media&token=5947626d-7d8a-4b17-86fc-b9bfbd587042)","-","-","-","-","-");
        Map<String, Object> usermap = new HashMap<>();

        usermap.put("name", usern.getName());
        usermap.put("surname", usern.getSurname());
        usermap.put("gender", usern.getGender());
        usermap.put("email", usern.getEmail());
        usermap.put("graduationYear",usern.getGraduateYear());
        usermap.put("password",usern.getPassword());
        usermap.put("photo",usern.getPhotoID());
        usermap.put("country",usern.getCountry());
        usermap.put("job infos:",usern.getJobInfos());
        usermap.put("social media infos:",usern.getSocialMediaInfos());
        usermap.put("phone number:",usern.getPhoneNumber());
        usermap.put("second email:",usern.getSecondEmail());

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                db.collection("users")
                        .add(usermap)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("FirebaseTest", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("FirebaseTest", "Error adding document", e);
                            }
                        });

                Toast.makeText(MainActivity.this,"Kullanıcı Oluşturuldu! Profilinizi güncellemeniz gerekiyor!",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this,CreateProfile.class);
                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });



    }
   public void adduserstoDatabase(){
       FirebaseFirestore db = FirebaseFirestore.getInstance();
       List<Map<String, Object>> users = new ArrayList<>();



       User u1 = new User("Ayı", "Abbas", "erkek", "ayiabbas@hotmail.com", "2008", "asdasd", "https://firebasestorage.googleapis.com/v0/b/newfirebasedeneme.appspot.com/o/images%2Fayi_abbas.jpg?alt=media&token=611f41fd-5520-4351-a1bf-abf57c03bf32","Türkiye","Kabadayı","facebook: www.facebook.com/ayiabbas","02453235621","ayiabbas@gmail.com");
       User u2 = new User("Mülayim", "Sert", "erkek", "bombaci_mulayim@hotmail.com", "2009", "asdasdas", "https://firebasestorage.googleapis.com/v0/b/newfirebasedeneme.appspot.com/o/images%2Fbombaci_mulayim.jpg?alt=media&token=a668d2fe-2a66-4622-8d8d-352c11579d94","Türkiye","Oyuncu","facebook: www.facebook.com/","02453233515","bombaciMulayim@gmail.com");
       User u3 = new User("Dikiş Tutmaz", "Sabri", "erkek", "dikistutmaz@outlook.com", "2002", "dikis", "https://firebasestorage.googleapis.com/v0/b/newfirebasedeneme.appspot.com/o/images%2Fdikistutmaz_sabri.jpg?alt=media&token=0dfb9f7d-6fb8-4e55-96a5-eb206040ea4d","Türkiye","Mafya Babası","-","05323235620","dikistutmaz@outlook.com");
       User u4 = new User("Gaddar", "Kerim", "erkek", "gaddar_keRim@hotmail.com", "2010", "hepiniziSeverim", "https://firebasestorage.googleapis.com/v0/b/newfirebasedeneme.appspot.com/o/images%2Fgaddar_kerim.jpg?alt=media&token=d20c91fc-fbf7-414b-a438-29d13663fd30","Türkiye","Kiralık Katil","facebook: www.facebook.com/gaddarKerim instagram: www.instagram.com/gaddarKerim","06542350123","-");
       User u5 = new User("Gardrop", "Fuat", "erkek", "gardrop@hotmail.com", "2006", "gardolap", "https://firebasestorage.googleapis.com/v0/b/newfirebasedeneme.appspot.com/o/images%2Fgardrop_fuat.jpg?alt=media&token=1ac9cbd8-a5e0-44e2-b76f-8c25cc951574","Türkiye","Oyuncu","facebook: www.instagram.com/gardropFuat","05421345671","gardolapFoat@gmail.com");
       User u6 = new User("Karbonat", "Erol", "erkek", "karbonat@hotmail.com", "2008", "karbonatçıyım", "https://firebasestorage.googleapis.com/v0/b/newfirebasedeneme.appspot.com/o/images%2Fkarbonat_erol.jpg?alt=media&token=f4ca2e96-05fe-4573-826f-b4f27cb2150a","Türkiye","Karbonatçı","facebook: www.facebook.com/kErol","0563127033","kerol@gmail.com");
       User u7 = new User("Kartal", "Tibet", "erkek", "ktibet@hotmail.com", "2007", "ktibet", "https://firebasestorage.googleapis.com/v0/b/newfirebasedeneme.appspot.com/o/images%2Fkartal_tibet.jpg?alt=media&token=4883e8dc-3625-4653-8b55-e194424be687","Türkiye","Oyuncu","facebook: www.facebook.com/ktibet","05123456789","kartal_tibet@gmail.com");
       User u8 = new User("Papağan", "Ziya", "erkek", "ziya@hotmail.com", "2012", "atmaziya", "https://firebasestorage.googleapis.com/v0/b/newfirebasedeneme.appspot.com/o/images%2Fpapagan_ziya.jpg?alt=media&token=ad014b3c-199f-499c-abd5-515d350a8c9f","Güney Afrika","-","-","-","-");
       User u9 = new User("Sansar", "Selim", "erkek", "sansar_selim@hotmail.com", "2000", "sansarselim", "https://firebasestorage.googleapis.com/v0/b/newfirebasedeneme.appspot.com/o/images%2Fsansar_selim.jpg?alt=media&token=45cc9ed2-b718-49bc-a3f1-7ac9ff9e0108","Türkiye","Muhbir","facebook: www.facebook.com/s.selim","03235631056","-");
       User u10 = new User("Susta", "Kazım", "erkek", "susta_kazım@hotmail.com", "2001", "sustaKazım", "https://firebasestorage.googleapis.com/v0/b/newfirebasedeneme.appspot.com/o/images%2Fsusta_kazim.jpg?alt=media&token=e58e2b7c-0afb-4749-8105-9762fee46a71","Türkiye","Dikişçi","facebook: www.facebook.com/s.kazım instagram: www.instagram.com/susta","03545137509","sustaKazım@gmail.com");

       Map<String, Object> user1 = new HashMap<>();
       user1.put("name", u1.getName());
       user1.put("surname", u1.getSurname());
       user1.put("gender", u1.getGender());
       user1.put("email", u1.getEmail());
       user1.put("graduationYear",u1.getGraduateYear());
       user1.put("password",u1.getPassword());
       user1.put("photo",u1.getPhotoID());
       user1.put("country",u1.getCountry());
       user1.put("job infos:",u1.getJobInfos());
       user1.put("social media infos:",u1.getSocialMediaInfos());
       user1.put("phone number:",u1.getPhoneNumber());
       user1.put("second email:",u1.getSecondEmail());



       Map<String, Object> user2 = new HashMap<>();
       user2.put("name", u2.getName());
       user2.put("surname", u2.getSurname());
       user2.put("gender", u2.getGender());
       user2.put("email", u2.getEmail());
       user2.put("graduationYear",u2.getGraduateYear());
       user2.put("password",u2.getPassword());
       user2.put("photo",u2.getPhotoID());
       user2.put("country",u2.getCountry());
       user2.put("job infos:",u2.getJobInfos());
       user2.put("social media infos:",u2.getSocialMediaInfos());
       user2.put("phone number:",u2.getPhoneNumber());
       user2.put("second email:",u2.getSecondEmail());


       Map<String, Object> user3 = new HashMap<>();
       user3.put("name", u3.getName());
       user3.put("surname", u3.getSurname());
       user3.put("gender", u3.getGender());
       user3.put("email", u3.getEmail());
       user3.put("graduationYear",u3.getGraduateYear());
       user3.put("password",u3.getPassword());
       user3.put("photo",u3.getPhotoID());
       user3.put("country",u3.getCountry());
       user3.put("job infos:",u3.getJobInfos());
       user3.put("social media infos:",u3.getSocialMediaInfos());
       user3.put("phone number:",u3.getPhoneNumber());
       user3.put("second email:",u3.getSecondEmail());

       Map<String, Object> user4 = new HashMap<>();
       user4.put("name", u4.getName());
       user4.put("surname", u4.getSurname());
       user4.put("gender", u4.getGender());
       user4.put("email", u4.getEmail());
       user4.put("graduationYear",u4.getGraduateYear());
       user4.put("password",u4.getPassword());
       user4.put("photo",u4.getPhotoID());
       user4.put("country",u4.getCountry());
       user4.put("job infos:",u4.getJobInfos());
       user4.put("social media infos:",u4.getSocialMediaInfos());
       user4.put("phone number:",u4.getPhoneNumber());
       user4.put("second email:",u4.getSecondEmail());

       Map<String, Object> user5 = new HashMap<>();
       user5.put("name", u5.getName());
       user5.put("surname", u5.getSurname());
       user5.put("gender", u5.getGender());
       user5.put("email", u5.getEmail());
       user5.put("graduationYear",u5.getGraduateYear());
       user5.put("password",u5.getPassword());
       user5.put("photo",u5.getPhotoID());
       user5.put("country",u5.getCountry());
       user5.put("job infos:",u5.getJobInfos());
       user5.put("social media infos:",u5.getSocialMediaInfos());
       user5.put("phone number:",u5.getPhoneNumber());
       user5.put("second email:",u5.getSecondEmail());

       Map<String, Object> user6 = new HashMap<>();
       user6.put("name", u6.getName());
       user6.put("surname", u6.getSurname());
       user6.put("gender", u6.getGender());
       user6.put("email", u6.getEmail());
       user6.put("graduationYear",u6.getGraduateYear());
       user6.put("password",u6.getPassword());
       user6.put("photo",u6.getPhotoID());
       user6.put("country",u6.getCountry());
       user6.put("job infos:",u6.getJobInfos());
       user6.put("social media infos:",u6.getSocialMediaInfos());
       user6.put("phone number:",u6.getPhoneNumber());
       user6.put("second email:",u6.getSecondEmail());

       Map<String, Object> user7 = new HashMap<>();
       user7.put("name", u7.getName());
       user7.put("surname", u7.getSurname());
       user7.put("gender", u7.getGender());
       user7.put("email", u7.getEmail());
       user7.put("graduationYear",u7.getGraduateYear());
       user7.put("password",u7.getPassword());
       user7.put("photo",u7.getPhotoID());
       user7.put("country",u7.getCountry());
       user7.put("job infos:",u7.getJobInfos());
       user7.put("social media infos:",u7.getSocialMediaInfos());
       user7.put("phone number:",u7.getPhoneNumber());
       user7.put("second email:",u7.getSecondEmail());


       Map<String, Object> user8 = new HashMap<>();
       user8.put("name", u8.getName());
       user8.put("surname", u8.getSurname());
       user8.put("gender", u8.getGender());
       user8.put("email", u8.getEmail());
       user8.put("graduationYear",u8.getGraduateYear());
       user8.put("password",u8.getPassword());
       user8.put("photo",u8.getPhotoID());
       user8.put("country",u8.getCountry());
       user8.put("job infos:",u8.getJobInfos());
       user8.put("social media infos:",u8.getSocialMediaInfos());
       user8.put("phone number:",u8.getPhoneNumber());
       user8.put("second email:",u8.getSecondEmail());


       Map<String, Object> user9 = new HashMap<>();
       user9.put("name", u9.getName());
       user9.put("surname", u9.getSurname());
       user9.put("gender", u9.getGender());
       user9.put("email", u9.getEmail());
       user9.put("graduationYear",u9.getGraduateYear());
       user9.put("password",u9.getPassword());
       user9.put("photo",u9.getPhotoID());
       user9.put("country",u9.getCountry());
       user9.put("job infos:",u9.getJobInfos());
       user9.put("social media infos:",u9.getSocialMediaInfos());
       user9.put("phone number:",u9.getPhoneNumber());
       user9.put("second email:",u9.getSecondEmail());


       Map<String, Object> user10 = new HashMap<>();
       user10.put("name", u10.getName());
       user10.put("surname", u10.getSurname());
       user10.put("gender", u10.getGender());
       user10.put("email", u10.getEmail());
       user10.put("graduationYear",u10.getGraduateYear());
       user10.put("password",u10.getPassword());
       user10.put("photo",u10.getPhotoID());
       user10.put("country",u10.getCountry());
       user10.put("job infos:",u10.getJobInfos());
       user10.put("social media infos:",u10.getSocialMediaInfos());
       user10.put("phone number:",u10.getPhoneNumber());
       user10.put("second email:",u10.getSecondEmail());


       users.add(user1);
       users.add(user2);
       users.add(user3);
       users.add(user4);
       users.add(user5);
       users.add(user6);
       users.add(user7);
       users.add(user8);
       users.add(user9);
       users.add(user10);


               for (Map<String, Object> user : users) {

                   db.collection("users")
                           .add(user)
                           .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                               @Override
                               public void onSuccess(DocumentReference documentReference) {
                                   Log.d("FirebaseTest", "DocumentSnapshot added with ID: " + documentReference.getId());
                               }
                           })
                           .addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Log.w("FirebaseTest", "Error adding document", e);
                               }
                           });
               }
           }
       }






