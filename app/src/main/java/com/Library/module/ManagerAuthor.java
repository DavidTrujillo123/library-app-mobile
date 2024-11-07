package com.Library.module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Library.entites.Author;

public class ManagerAuthor {
    final private SqlLiteAdmin sqlAdmin;
    final private SQLiteDatabase db;

    public ManagerAuthor(Context context) {
        sqlAdmin = new SqlLiteAdmin(context, "Library", null, 1);
        db = sqlAdmin.getWritableDatabase();
    }

    public void create(Author author) throws Exception {
        Author authorFind = readById(author.getIdAuthor());
        if(authorFind != null){
            throw new Exception("El id del autor ya existe");
        }

        ContentValues values = new ContentValues();
        values.put("id_author", author.getIdAuthor());
        values.put("name", author.getName());
        values.put("surname", author.getSurname());
        values.put("isoCountry", author.getIsoCounty());
        values.put("age", author.getAge());

        long result = db.insert("author", null, values);

        if (result == -1) throw new Exception("Eror al crear el autor");;
    }

    public void update(Author author) throws Exception {

        Author authorFind = readById(author.getIdAuthor());
        if(authorFind == null){
            throw new Exception("El id del autor no existe");
        }

        ContentValues values = new ContentValues();
        values.put("name", author.getName());
        values.put("surname", author.getSurname());
        values.put("isoCountry", author.getIsoCounty());
        values.put("age", author.getAge());

        int result = db.update("author", values, "id_author=" + author.getIdAuthor(),
                null);

        if(result == 0){
            throw new Exception("Error al actualizar el autor");
        }
    }

    public void delete(int id) throws Exception {
        Author authorFind = readById(id);
        if(authorFind == null){
            throw new Exception("El id del autor no existe");
        }

        int result = db.delete("author", "id_author=" + id, null);

        if(result == 0){
            throw new Exception("Error al eliminar el autor");
        }
    }

    public Author[] ReadAll(){
        Author author;
        Author[] listAuthor;

        int i = 0;

        String query = "SELECT * FROM author ORDER BY surname, name";
        Cursor cursor = db.rawQuery(query, null);

        listAuthor = new Author[cursor.getCount()];

        while( cursor.moveToNext() ){
            author = new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getInt(4));
            listAuthor[i] = author;
            i++;
        }
        cursor.close();

        return listAuthor;
    }

    public Author readById(int id){
        String query = "SELECT * FROM author WHERE id_author="+id;
        Cursor cursor = db.rawQuery(query, null);

        Author result = null;

        if(cursor.moveToFirst()){
            result = new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
        }

        cursor.close();

        return result;
    }

}
