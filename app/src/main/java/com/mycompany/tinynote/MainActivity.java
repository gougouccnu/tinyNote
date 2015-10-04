package com.mycompany.tinynote;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private TextViewVertical titleYear;
    private Button buttonWrite;
    private TextViewVertical titleMonth;

    private List<NotesItem> notesItemList = new ArrayList<NotesItem>();

    //private TextViewVertical textViewVertical = new TextViewVertical(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleYear = (TextViewVertical) findViewById(R.id.title_year);
        buttonWrite = (Button) findViewById(R.id.button_write);
        titleMonth = (TextViewVertical) findViewById(R.id.title_month);
        //TextViewVertical
        //textViewVertical.setText("2015年");
        titleYear.setText("二零一五年");

        initNotesItem();
        NotesItemAdapter adapter = new NotesItemAdapter(MainActivity.this,
                R.layout.note_item, notesItemList);
        HorizontalScrollListView listView = (HorizontalScrollListView) findViewById(R.id.note_item);
        listView.setAdapter(adapter);

        //write display
        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteNoteActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initNotesItem() {
        NotesItem item1 = new NotesItem("一日");
        notesItemList.add(item1);
        NotesItem item2 = new NotesItem("三日记录");
        notesItemList.add(item2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
