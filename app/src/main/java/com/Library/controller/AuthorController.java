package com.Library.controller;

import android.content.Context;
import android.widget.Toast;

import com.Library.entites.Author;
import com.Library.module.ManagerAuthor;

public class AuthorController {
    final private ManagerAuthor managerAuthor;
    private Author[] listAuthor;
    private Author author;

    public AuthorController(Context c) {
        managerAuthor = new ManagerAuthor(c);
        author = new Author();
    }

    public void ReadById(int id){
        author  = managerAuthor.readById(id);
    }

    public void create(Context context)
    {
        try {
            managerAuthor.create(author);
            Toast.makeText(context, "Autor creado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void update(Context context){
        try {
            managerAuthor.update(author);
            Toast.makeText(context, "Autor actualizado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(Context context) {
        try {
            managerAuthor.delete(author.getIdAuthor());
            Toast.makeText(context, "Autor eliminado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
