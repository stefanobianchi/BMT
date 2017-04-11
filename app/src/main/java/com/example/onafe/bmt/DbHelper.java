package com.example.onafe.bmt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by onafe on 05/04/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    SQLiteDatabase myDB;

    private static final String DBName ="my.dbtimesheet";
    private static final int Version = 1 ;

    private static final String TableName ="configurazione";
    private static final String ID="_id";
    private static final String Descrizione="descrizione";
    private static final String Colore="colore";
    private static final String Entrata="entrata";
    private static final String Uscita="uscita";
    private static final String Pausa="pausa";
    private static final String Assenza="assenza";
    private static final String OraAssenza="ora_assenza";


    public DbHelper(Context context) {
        super(context, DBName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String createQuery="Create Table " + TableName + " ( " +
                ID + " INTEGER PRIMARY KEY, "+
                Descrizione +" TEXT NOT NULL, "+
                Colore +" INTEGER NOT NULL, "+
                Entrata +" TEXT NOT NULL, "+
                Uscita +" TEXT NOT NULL, "+
                Pausa +" INTEGER NOT NULL, "+
                Assenza +" TEXT NOT NULL, "+
                OraAssenza +" INTEGER NOT NULL " +
                ")";
        db.execSQL(createQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void openDb(){
        myDB = getWritableDatabase();
    }

    public void closeDb(){
        if(myDB!= null && myDB.isOpen()){
            myDB.close();
        }
    }

    public long insert (int id,String descrizione,int colore, String entrata, String uscita, int pausa, String assenza, int oraAssenza){
        ContentValues values = new ContentValues();
        if(id != -1)
          values.put(ID,id);

        values.put(Descrizione,descrizione);
        values.put(Colore,colore);
        values.put(Entrata,entrata);
        values.put(Uscita,uscita);
        values.put(Pausa,pausa);
        values.put(Assenza,assenza);
        values.put(OraAssenza,oraAssenza);

        return   myDB.insert(TableName,null,values);

    }

    public long update (int id,String descrizione,int colore, String entrata, String uscita, int pausa, String assenza, int oraAssenza){
        ContentValues values = new ContentValues();

        values.put(Descrizione,descrizione);
        values.put(Colore,colore);
        values.put(Entrata,entrata);
        values.put(Uscita,uscita);
        values.put(Pausa,pausa);
        values.put(Assenza,assenza);
        values.put(OraAssenza,oraAssenza);

        String whereCause = ID + " = " + id;

        return   myDB.update(TableName,values,whereCause,null);

    }

    public long delete (int id){

        String whereCause = ID + " = " + id;

        return   myDB.delete(TableName,whereCause,null);

    }

    public Cursor getAllRecord(){

        String query="SELECT * FROM "+ TableName;
        return myDB.rawQuery(query,null);
    }
}
