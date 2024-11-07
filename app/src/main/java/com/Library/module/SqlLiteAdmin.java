package com.Library.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SqlLiteAdmin extends SQLiteOpenHelper {
    public SqlLiteAdmin(@Nullable Context context,
                        @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory,
                        int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS author (" +
                "id_author INT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "surname VARCHAR(50), " +
                "isoCountry CHAR(5), " +
                "age INT);");

        //        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS libros;");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS book (" +
                "id_book INTEGER PRIMARY KEY, " +
                "title TEXT(100), " +
                "id_author INTEGER, " +
                "isbn TEXT(30), " +
                "yearPublication INTEGER, " +
                "review INTEGER, " +
                "numSheets INTEGER, " +
                "FOREIGN KEY (id_author) REFERENCES author(id) " +
                "ON DELETE CASCADE ON UPDATE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
