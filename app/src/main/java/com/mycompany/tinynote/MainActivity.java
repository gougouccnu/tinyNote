package com.mycompany.tinynote;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private TextViewVertical titleYear;
    private Button buttonWrite;
    private TextViewVertical titleMonth;

    private MyDatabaseHelper dbHelper;
    private String year,month,title;

    private List<NotesItem> notesItemList = new ArrayList<NotesItem>();

    //private TextViewVertical textViewVertical = new TextViewVertical(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this, "NoteStore.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //查询NOTE表中最近月份的笔记并显示出来，
        //db.rawQuery("select id,year,month from Note where id = 1", null);
        Cursor cursor = db.query("Note", new String[] {"id","year","month"},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            year = cursor.getString(cursor.getColumnIndex("year"));
            month = cursor.getString(cursor.getColumnIndex("month"));
            //查询最近月份的笔记
            //db.rawQuery("select title from Note where location =?","wuhan" null);
            Cursor cursor2 = db.query("Note", new String[] {"title"}, "month=?", new String[] { "十月" },
                    null,null,null);
            if (cursor2.moveToFirst()) {
                do {
                    //遍历cursor对象，取出数据
                    title = cursor2.getString(cursor2.getColumnIndex("title"));
                    //添加到note list中
                    NotesItem item = new NotesItem(title);
                    notesItemList.add(item);
                } while (cursor2.moveToNext());
            }
        } else { //若表为空，则显示当前年月
            year = "二零一五年";
            month = "十月";
        }
        cursor.close();

        titleYear = (TextViewVertical) findViewById(R.id.title_year);
        buttonWrite = (Button) findViewById(R.id.button_write);
        titleMonth = (TextViewVertical) findViewById(R.id.title_month);
        //TextViewVertical
        //textViewVertical.setText("2015年");
        titleYear.setText(year);
        titleMonth.setText(month);


        NotesItemAdapter adapter = new NotesItemAdapter(MainActivity.this,
                R.layout.note_item, notesItemList);
        HorizontalScrollListView listView = (HorizontalScrollListView) findViewById(R.id.note_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NotesItem item = notesItemList.get(position);
                // 启动日记查看编辑活动，同时将日记title,month传递过去
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra("extra_noteTitle", item.getTitle());
                intent.putExtra("extra_noteMonth", month);
                startActivity(intent);
            }
        });

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
