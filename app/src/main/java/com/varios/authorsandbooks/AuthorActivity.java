package com.varios.authorsandbooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.Library.controller.AuthorController;
import com.Library.entites.Author;

public class AuthorActivity extends AppCompatActivity {

    private AuthorController controller;
    private EditText txtSurname, txtName, txtId, txtIsoCountry, txtAge;
    TextView lblUser;


    public void backOnClick(View v){
        finish();
    }

    public void changeViewBooks(View v) {
        Intent intent = new Intent(this, BookActivity.class);
        intent.putExtra("autorName", txtName.getText().toString()+" "+txtSurname.getText().toString());
        intent.putExtra("idAutor", txtId.getText().toString());
        startActivity(intent);
    }

    private Author getViewInfoAuthor(){
        int id = Integer.parseInt(txtId.getText().toString());
        String name = txtName.getText().toString();
        String surname = txtSurname.getText().toString();
        String isoCountry = txtIsoCountry.getText().toString();
        int age = Integer.parseInt(txtAge.getText().toString());

        return new Author(id, name, surname, isoCountry, age);
    }

    public void createOnClick(View v){
        controller.setAuthor(getViewInfoAuthor());

        controller.create(this);;
    }

    public void updateOnclick(View v){
        controller.setAuthor(getViewInfoAuthor());

        controller.update(this);
    }

    public void deleteOnClick(View v) {
        controller.setAuthor(getViewInfoAuthor());
        controller.delete(this);
    }

    public void readById(View v){
        int id = Integer.parseInt(txtId.getText().toString());
        controller.ReadById(id);
        Author author = controller.getAuthor();
        if(author != null){
            txtName.setText(controller.getAuthor().getName());
            txtSurname.setText(controller.getAuthor().getSurname());
            txtAge.setText(String.valueOf(controller.getAuthor().getAge()));
            txtIsoCountry.setText(controller.getAuthor().getIsoCounty());
            txtId.setText(String.valueOf(controller.getAuthor().getIdAuthor()));
            Toast.makeText(this, "Autor encontrado", Toast.LENGTH_SHORT).show();
        }else{
            clear();
            Toast.makeText(this, "No existe un autor con ese ID", Toast.LENGTH_SHORT).show();
        }

    }

    private void clear(){
        txtId.setText("");
        txtName.setText("");
        txtSurname.setText("");
        txtAge.setText("");
        txtIsoCountry.setText("");
    }
    public void initVariables(){
        txtId = findViewById(R.id.input_id);
        txtName = findViewById(R.id.input_nombres);
        txtSurname = findViewById(R.id.input_apellidos);
        txtAge = findViewById(R.id.input_edad);
        txtIsoCountry = findViewById(R.id.input_isoPais);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_author);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        controller = new AuthorController(this);

        initVariables();
    }


}