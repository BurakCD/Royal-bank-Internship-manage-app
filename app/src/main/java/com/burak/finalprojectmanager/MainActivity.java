package com.burak.finalprojectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nameSurname, class_no, mail, age, sector;
    GridView gv_list;
    GridViewAdapter GridViewAdapter;
    DatabaseReference connection = FirebaseDatabase.getInstance().getReference("Internship");
    Internship selected;
    String acceptance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameSurname = (EditText)findViewById(R.id.nameSurname);
        class_no = (EditText)findViewById(R.id.Class_no);
        mail = (EditText) findViewById(R.id.mail);
        age = (EditText) findViewById(R.id.age);
        sector = (EditText) findViewById(R.id.sector);
        gv_list = (GridView) findViewById(R.id.gv_list);

        ArrayList<Internship> databasedata = new ArrayList<Internship>();

        connection.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                databasedata.clear();

                for (DataSnapshot data:snapshot.getChildren()){

                    databasedata.add(new Internship(data.child("id").getValue().toString(),
                            data.child("name").getValue().toString(),
                            data.child("class_no").getValue().toString(),
                            data.child("mail").getValue().toString(),
                            data.child("age").getValue().toString(),
                            data.child("sector").getValue().toString(),
                            data.child("acceptance").getValue().toString()));
                }

                GridViewAdapter = new GridViewAdapter(MainActivity.this,databasedata);
                gv_list.setAdapter(GridViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Veritabanıyla iletişim sağlanamadı!",Toast.LENGTH_SHORT).show();
            }
        });

        gv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selected = GridViewAdapter.getItem(i);
                nameSurname.setText(selected.getName());
                class_no.setText(selected.getClass_no());
                mail.setText(selected.getMail());
                age.setText(selected.getAge());
                sector.setText(selected.getSector());
                acceptance = selected.getAcceptance();

                Toast.makeText(getApplicationContext(),"Seçildi",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveToDatabase(View view){
        if (nameSurname.getText().toString().equals("")||
                class_no.getText().toString().equals("")||
                mail.getText().toString().equals("")||
                age.getText().toString().equals("")||
                sector.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(),"Alanlar boş bırakılamaz!",Toast.LENGTH_SHORT).show();

        }else {
            String id = connection.push().getKey();
            Internship newIntern = new Internship(id, nameSurname.getText().toString(), class_no.getText().toString(),
                    mail.getText().toString(), age.getText().toString(), sector.getText().toString(), "Aksiyon bekliyor...");
            connection.child(id).setValue(newIntern);

            nameSurname.setText("");
            class_no.setText("");
            mail.setText("");
            age.setText("");
            sector.setText("");

            Toast.makeText(getApplicationContext(), "Kaydınız başarıyla alınmıştır.", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateToDatabase(View view) {
        if (nameSurname.getText().toString().equals("")||
                class_no.getText().toString().equals("")||
                mail.getText().toString().equals("")||
                age.getText().toString().equals("")||
                sector.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(),"Alanlar boş bırakılamaz!",Toast.LENGTH_SHORT).show();

        }else {
            String id = selected.getId();
            Internship newIntern = new Internship(id, nameSurname.getText().toString(),
                    class_no.getText().toString(),
                    mail.getText().toString(),
                    age.getText().toString(),
                    sector.getText().toString(), acceptance);
            connection.child(id).setValue(newIntern);

            nameSurname.setText("");
            class_no.setText("");
            mail.setText("");
            age.setText("");
            sector.setText("");

            Toast.makeText(getApplicationContext(), "Kayıt başarıyla güncellenmiştir.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteFromDatabase(View view) {
        if (nameSurname.getText().toString().equals("")||
                class_no.getText().toString().equals("")||
                mail.getText().toString().equals("")||
                age.getText().toString().equals("")||
                sector.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(),"Alanlar boş bırakılamaz!",Toast.LENGTH_SHORT).show();

        }else {
            String id = selected.getId();
            connection.child(id).removeValue();

            nameSurname.setText("");
            class_no.setText("");
            mail.setText("");
            age.setText("");
            sector.setText("");

            Toast.makeText(getApplicationContext(), "Kayıt başarıyla silinmiştir.", Toast.LENGTH_SHORT).show();
        }
    }

    public void acceptStatus(View view){
        if (nameSurname.getText().toString().equals("")||
                class_no.getText().toString().equals("")||
                mail.getText().toString().equals("")||
                age.getText().toString().equals("")||
                sector.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(),"Alanlar boş bırakılamaz!",Toast.LENGTH_SHORT).show();

        }else {
            String id = selected.getId();
            Internship newIntern = new Internship(id, nameSurname.getText().toString(),
                    class_no.getText().toString(),
                    mail.getText().toString(),
                    age.getText().toString(),
                    sector.getText().toString(), "Kabul edildi.");
            connection.child(id).setValue(newIntern);

            Toast.makeText(getApplicationContext(), "Stajyer başvurusu kabul edildi.", Toast.LENGTH_SHORT).show();
        }
    }

    public void rejectStatus(View view){
        if (nameSurname.getText().toString().equals("")||
                class_no.getText().toString().equals("")||
                mail.getText().toString().equals("")||
                age.getText().toString().equals("")||
                sector.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(),"Alanlar boş bırakılamaz!",Toast.LENGTH_SHORT).show();

        }else {
            String id = selected.getId();
            Internship newIntern = new Internship(id, nameSurname.getText().toString(),
                    class_no.getText().toString(),
                    mail.getText().toString(),
                    age.getText().toString(),
                    sector.getText().toString(), "Reddedildi.");
            connection.child(id).setValue(newIntern);

            Toast.makeText(getApplicationContext(), "Stajyer başvurusu reddedildi.", Toast.LENGTH_SHORT).show();
        }
    }

}