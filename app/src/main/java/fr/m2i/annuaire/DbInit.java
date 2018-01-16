package fr.m2i.annuaire;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrateur on 16/01/2018.
 */

//toujours fait avec un constructeur  et 2 méthodes
public class DbInit extends SQLiteOpenHelper{


    // on simplifie le constructeur pour ne laisser que le contaxt paramétrable
    public DbInit(Context context) {
        super(context, "annuaire", null, 1);
    }

    //code onCreat obligatoire pour créer la base de daonnée une seule fois
    //creation de la base de donnée

   @Override
    public void onCreate(SQLiteDatabase db) {

            String sql = "CREATE TABLE contacts (" +
                   " id INTEGER PRIMARY KEY NOT NULL" +
                   ", name TEXT NOT NULL" +
                   ", tel TEXT" +
                   ")";
           db.execSQL(sql);


       }

    //code qui sera appellé pour faire évoluer la base de données et la modification des tables
    // il identifie en fonction de la version d'enregistrement
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }


}


