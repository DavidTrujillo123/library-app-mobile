package com.Library.module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Library.entites.Author;
import com.Library.entites.Book;

import java.util.ArrayList;
import java.util.List;

public class ManagerBook {

    final private SqlLiteAdmin sqlAdmin;
    final private SQLiteDatabase db;

    public ManagerBook(Context context) {
        sqlAdmin = new SqlLiteAdmin(context, "Library", null, 1);
        db = sqlAdmin.getWritableDatabase();
    }

    public Book findById(int id) {
        String query = "SELECT * FROM book WHERE id_book="+id;
        Cursor cursor = db.rawQuery(query, null);

        Book result = null;

        if(cursor.moveToFirst()){
            result = new Book(
                    cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                    cursor.getString(3), cursor.getInt(4), cursor.getInt(5),
                    cursor.getInt(6)
            );
        }

        cursor.close();

        return result;
    }

    public void create(Book book) throws Exception {
        Book bookFind = findById(book.getIdBook());
        if(bookFind != null){
            throw new Exception("El id del libro ya existe");
        }

        ContentValues values = new ContentValues();

        values.put("id_book", book.getIdBook());
        values.put("title", book.getTitle());
        values.put("id_author", book.getIdAuthor());
        values.put("isbn", book.getIsbn());
        values.put("yearPublication", book.getYearPublication());
        values.put("review", book.getReview());
        values.put("numSheets", book.getNumSheets());

        long result = db.insert("book", null, values);
        if (result == -1) throw new Exception("Eror al crear el libro");;
    }

    public void update(Book book) throws Exception {
        Book bookFind = findById(book.getIdBook());
        if(bookFind == null){
            throw new Exception("El id del libro no existe");
        }
        ContentValues values = new ContentValues();
        values.put("title", book.getTitle());
        values.put("id_author", book.getIdAuthor());
        values.put("isbn", book.getIsbn());
        values.put("yearPublication", book.getYearPublication());
        values.put("review", book.getReview());
        values.put("numSheets", book.getNumSheets());

        long result = db.update("book", values, "id_book="+book.getIdBook(), null);
        if (result == -1) throw new Exception("Eror al actualizar el libro");
    }

    public List<Book> readAllBooksByAuthor(int idAuthor){
        String query = "SELECT * FROM book WHERE id_author ="+idAuthor;
        Cursor cursor = db.rawQuery(query, null);

        List<Book> listBooks = new ArrayList<>();
        int i = 0;

        while (cursor.moveToNext()) {
            listBooks.add(new Book(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6)
            ));
        }

        cursor.close();

        return listBooks;
    }
}
