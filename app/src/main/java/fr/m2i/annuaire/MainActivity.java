package fr.m2i.annuaire;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


   SQLiteDatabase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //ouverture / creation de lobjet SqliteDatabse
        DbInit dbinit = new DbInit(this);
        db = dbinit.getWritableDatabase();

    }

        /*
        principale métode de l'objet SqliteDatabase:
           execSQL() pour les SQL de mise à jour
           query() pour les SELECT
           update()
           insert()
           delete()
           beginTransation()    pour exectuter toute la mise à jour par bloc au cas où si ca plante en cours
           endTransaction()


    }*/

    public void search(View v){

        String columns[] = {"id","name","tel"};
        String where = "name = 'Alain'";


        Cursor cursor = db.query("contacts",columns, where,null,"","","");
        cursor.moveToFirst();
        String name = cursor.getString(1);
        Toast.makeText(this,name,Toast.LENGTH_LONG).show();

    }

    public void clear(View v){




    }
    public void delete(View v){


    }
   public void save(View v){





        ContentValues values = new ContentValues();
        //values.put("id",1);
        values.put("name","Alain");
        values.put("tel","015487452");

        db.insert("contacts",null,values);


    }


}
