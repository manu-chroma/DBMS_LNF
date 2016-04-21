package com.dbms.dbms_lnf;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class FoundActivity extends Activity implements OnClickListener {
    Button viewAll;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewAll = (Button)findViewById(R.id.viewAll);
        viewAll.setOnClickListener(this);
        db=openOrCreateDatabase("ItemDB", Context.MODE_PRIVATE, null);



    }

    @Override
    public void onClick(View v) {

        if(v == viewAll)
        {
            Cursor c=db.rawQuery("SELECT * FROM Found", null);
            if(c.getCount()==0)
            {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("Rollno: "+c.getString(0)+"\n");
                buffer.append("Name: "+c.getString(1)+"\n");
                buffer.append("Type: "+c.getString(2)+"\n");
                buffer.append("Contact: "+c.getString(3)+"\n");
                buffer.append("Place: "+c.getString(4)+"\n");
                buffer.append("Description :"+c.getString(5)+"\n\n");
            }
            showMessage("Lost items", buffer.toString());
        }



    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
