package com.varios.authorsandbooks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Library.controller.AuthorController;
import com.Library.controller.BookController;
import com.Library.entites.Book;
import com.varios.authorsandbooks.reciclyview.BookAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    private BookController controller;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extra = getIntent().getExtras();
        String idAuthor = extra.getString("idAutor");
        controller = new BookController(this, Integer.parseInt(idAuthor));

        recyclerView = findViewById(R.id.recicleview_table_books);

//
        recyclerView.setLayoutManager(new LinearLayoutManager(this));;
        recyclerView.setAdapter(controller.getAdapter());

    }

    public void createBook(View v){
        controller.setBook(new Book());
        controller.showForm(v, this);

    }
}
