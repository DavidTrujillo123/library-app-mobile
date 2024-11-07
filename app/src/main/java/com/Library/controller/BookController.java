package com.Library.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.Library.entites.Book;
import com.Library.module.ManagerBook;
import com.varios.authorsandbooks.R;
import com.varios.authorsandbooks.reciclyview.BookAdapter;

import java.util.List;

public class BookController {
    final private ManagerBook managerBook;
    private List<Book> listBooks;
    private Book book;
    private int idAuthor;
    private BookAdapter adapter;

    public BookController(Context c, int idAuthor) {
        managerBook = new ManagerBook(c);
        this.idAuthor = idAuthor;
        book = new Book();
        listBooks = managerBook.readAllBooksByAuthor(idAuthor);
        this.adapter = new BookAdapter(c, listBooks, this);
    }

    public void create(Context context){
        try {
            managerBook.create(book);
            listBooks.clear();
            listBooks.addAll(managerBook.readAllBooksByAuthor(idAuthor));
//            listBooks = managerBook.readAllBooksByAuthor(idAuthor);
            Toast.makeText(context, "Libro creado", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void update(Context context){
        try {
            managerBook.update(book);
            listBooks = managerBook.readAllBooksByAuthor(idAuthor);
            adapter.notifyDataSetChanged();
            Toast.makeText(context, "Libro actualizado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void readAllBooksByAuthor(){
        listBooks = managerBook.readAllBooksByAuthor(idAuthor);
    }

    public List<Book> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<Book> listBooks) {
        this.listBooks = listBooks;
    }


    public void showForm(View v, Context context) {
        // Inflar el layout del formulario
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_formulario, null);

        // Referencias a los campos de entrada
        EditText inputId = view.findViewById(R.id.lb_input_id);
        EditText inputTitulo = view.findViewById(R.id.lb_input_titulo);
        EditText inputIdAutor = view.findViewById(R.id.lb_input_idAutor);
        EditText inputIsbn = view.findViewById(R.id.lb_input_isbn);
        EditText inputAnioPublicacion = view.findViewById(R.id.lb_input_anioPublicacion);
        EditText inputRevision = view.findViewById(R.id.lb_input_revision);
        EditText inputNroHojas = view.findViewById(R.id.lb_input_nroHojas);

        inputIdAutor.setText(idAuthor+"");

        if(book.getIdBook() != 0){
            inputId.setText(book.getIdBook()+"");
            inputTitulo.setText(book.getTitle());
            inputIsbn.setText(book.getIsbn());
            inputAnioPublicacion.setText(book.getYearPublication()+"");
            inputRevision.setText(book.getReview()+"");
            inputNroHojas.setText(book.getNumSheets()+"");
        }

        // Crear el dialog con el formulario
        new AlertDialog.Builder(context)
                .setTitle("Formulario de Libro")
                .setView(view)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int id = Integer.parseInt(inputId.getText().toString());
                        String titulo = inputTitulo.getText().toString();
                        int idAutor = Integer.parseInt(inputIdAutor.getText().toString());
                        String isbn = inputIsbn.getText().toString();
                        int anioPublicacion = Integer.parseInt(inputAnioPublicacion.getText().toString());
                        int revision = Integer.parseInt(inputRevision.getText().toString());
                        int nroHojas = Integer.parseInt(inputNroHojas.getText().toString());


                        book.setTitle(titulo);
                        book.setIdAuthor(idAutor);
                        book.setIsbn(isbn);
                        book.setYearPublication(anioPublicacion);
                        book.setReview(revision);
                        book.setNumSheets(nroHojas);

                        if(book.getIdBook() != 0){
                            update(context);
                        }else{
                            book.setIdBook(id);
                            create(context);
                        }

                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public BookAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BookAdapter adapter) {
        this.adapter = adapter;
    }
}
