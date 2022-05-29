package com.example.agenda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static int version = 11;
    private static String nomeDB = "Agenda.db";

    //dados do banco: tabela contato -> id, nome,tel

    String[] sql = {
            "CREATE TABLE contato (id INTEGER AUTO_INCREMENT PRIMARY KEY,nome TEXT,tel TEXT);",
            "INSERT INTO contato VALUES(1, 'zLeonardo', '47 992807006')",
            "INSERT INTO contato VALUES(2, 'aLeonardo', '47 992807006')",
            "INSERT INTO contato VALUES(3, 'BLeonardo', '47 992807006')",
            "INSERT INTO contato VALUES(4, 'Marco', '47 992207008')"
    };

    public DBHelper(@Nullable Context context) {
        super(context, nomeDB, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i=0 ; i<sql.length; i++){
            db.execSQL(sql[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        version++;
        db.execSQL("DROP TABLE IF EXISTS contato;");
        onCreate(db);
    }

    public long Insert_Contato(String nome, String tel){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", nome);
        cv.put("tel", tel);

        return db.insert("contato", null, cv);
    }

    public long Update_Contato(int id, String nome, String tel){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", nome);
        cv.put("tel", tel);

        return db.update("contato", cv, "id=?",new String[]{String.valueOf(id)});
    }

    public long Delete_Contato(int id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("contato", "id=?", new String[]{String.valueOf(id)});
    }

    public Cursor SelectAll_Contato(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM contato ORDER BY 2 COLLATE NOCASE ASC", null);
    }

    public Cursor SelectByID_Contato(int id){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM contato WHERE id=?", new String[]{String.valueOf(id)});
    }
}