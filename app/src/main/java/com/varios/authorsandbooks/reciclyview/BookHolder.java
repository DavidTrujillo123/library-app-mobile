package com.varios.authorsandbooks.reciclyview;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.varios.authorsandbooks.R;

public class BookHolder extends RecyclerView.ViewHolder {
    TextView idBook, title, isbn, yearPublication, review, numSheets;
    Button btnUpdate, btnDelete;
    public BookHolder(@NonNull View itemView) {
        super(itemView);
        idBook = itemView.findViewById(R.id.id_book);
        title = itemView.findViewById(R.id.title_book);
        isbn = itemView.findViewById(R.id.isbn);
        yearPublication = itemView.findViewById(R.id.year_publication);
        review = itemView.findViewById(R.id.review);
        numSheets = itemView.findViewById(R.id.number_of_sheets);
        btnUpdate = itemView.findViewById(R.id.btn_update);
        btnDelete = itemView.findViewById(R.id.btn_delete);
    }
}
