package fr.m2i.annuaire;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


   SQLiteDatabase db;
   EditText textName,textTel,textId,txtSearchName,txtSearchId ;
ListView lv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textName = findViewById(R.id.txtName);
        textId = findViewById(R.id.txtId);
        textTel = findViewById(R.id.txtTel);
        txtSearchName = findViewById(R.id.txtSearchName);
        txtSearchId=findViewById(R.id.txtSearchId);



        //ouverture / creation de lobjet SqliteDatabse
        DbInit dbinit = new DbInit(this);
        db = dbinit.getWritableDatabase();


        /*
        principale métode de l'objet SqliteDatabase:
           execSQL() pour les SQL de mise à jour
           query() pour les SELECT
           update()
           insert()
           delete()
           beginTransation()    pour exectuter toute la mise à jour par bloc au cas où si ca plante en cours
           endTransaction()*/




    }




    public void searchName(View v){

        String columns[] = {"id","name","tel"};
        String where = "name = '" + txtSearchName.getText().toString() + "'";


        Cursor cursor = db.query("contacts",columns, where,null,null,null,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            textId.setText(cursor.getString(0));
            textName.setText(cursor.getString(1));
            textTel.setText(cursor.getString(2));


        }else {
            Toast.makeText(this, "non trouvé", Toast.LENGTH_LONG).show();

        }
    }


    public void searchId(View v){


        String columns[] = {"id","name","tel"};
        String where = "id = " + txtSearchId.getText().toString() ;


        Cursor cursor = db.query("contacts",columns, where,null,null,null,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            textId.setText(cursor.getString(0));
            textName.setText(cursor.getString(1));
            textTel.setText(cursor.getString(2));


        }else {
            Toast.makeText(this, "non trouvé", Toast.LENGTH_LONG).show();

        }


    }

    public void clear(View v){

        textTel.setText("");
        textId.setText("");
        textName.setText("");
        txtSearchName.setText("");
        txtSearchId.setText("");




    }
    public void delete(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("confirmer la suppression");
        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.delete("contacts", "id = "+textId.getText().toString(),null);
                clear(null);

            }
        });

        builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();





    }
   public void save(View v){



        ContentValues values = new ContentValues();

        values.put("name",textName.getText().toString());
        values.put("tel",textTel.getText().toString());

       if(textId.getText().toString().equals("")){

            db.insert("contacts",null,values);
            Toast.makeText(this, " ajout ok  ", Toast.LENGTH_LONG).show();
       }else {

            update(values,textId.getText().toString());
            Toast.makeText(this, " modification ok  ", Toast.LENGTH_LONG).show();
        }


    }

    public void update(ContentValues values,String textId){

       db.update("contacts", values, "id = " + textId, null  );



    }



}
