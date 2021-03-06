package com.mycompany.tinynote;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private TextViewVertical titleYear;
    private Button buttonWrite;
    private TextViewVertical titleMonth;

    private MyDatabaseHelper dbHelper;
    private String year,month,title;

    private HorizontalListViewAdapter hlva;
    private HorizontalListView hlv;

    public List<NotesItem> notesItemList = new ArrayList<NotesItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hlv = (HorizontalListView)findViewById(R.id.note_item);

        //Typeface customFont = Typeface.createFromAsset(this.getAssets(), "fonts/KangXi.ttf");
        //hlv = (HorizontalListView) findViewById(R.id.note_item);
        //hlv.setTypeface(customFont);

        hlva = new HorizontalListViewAdapter(this, notesItemList);
        // inner class
        //A a = new A();
        //A.B ab = a.new B();
        HorizontalListViewAdapter.changeFont hlvaChangeFont = hlva.new changeFont();
        hlvaChangeFont.execute("");

        //hlva.notifyDataSetChanged();
        hlv.setAdapter(hlva);
        new LongOperation().execute("");

        hlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
//				if(olderSelectView == null){
//					olderSelectView = view;
//				}else{
//					olderSelectView.setSelected(false);
//					olderSelectView = null;
//				}
//				olderSelectView = view;
//				view.setSelected(true);
                //previewImg.setImageResource(ids[position]);
                //hlva.setSelectIndex(position);
                //hlva.notifyDataSetChanged();
                NotesItem item = notesItemList.get(position);
                // 启动日记查看编辑活动，同时将日记title,month传递过去
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra("extra_noteTitle", item.getTitle());
                intent.putExtra("extra_noteMonth", month);
                startActivity(intent);
            }
        });

        /*
        dbHelper = new MyDatabaseHelper(this, "NoteStore.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //查询NOTE表中最近月份的笔记并显示出来，
        //db.rawQuery("select id,year,month from Note where id = 1", null);
        Cursor cursor = db.query("Note", new String[]{"id", "year", "month"},
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
                    //noteItemList.
                } while (cursor2.moveToNext());
            }
        } else { //若表为空，则显示当前年月
            year = "二零一五年";
            month = "十月";
        }
        cursor.close();
    */
     /*   Typeface customFont = Typeface.createFromAsset(this.getAssets(), "fonts/KangXi.ttf");
        titleYear = (TextViewVertical) findViewById(R.id.title_year);
        titleYear.setTypeface(customFont);


        // titleYear = (TextViewVertical) findViewById(R.id.title_year);
        buttonWrite = (Button) findViewById(R.id.button_write);

        //titleMonth = (TextViewVertical) findViewById(R.id.title_month);
        //Typeface customFont = Typeface.createFromAsset(this.getAssets(), "fonts/KangXi.ttf");
        titleMonth = (TextViewVertical) findViewById(R.id.title_month);
        titleMonth.setTypeface(customFont);

        //TextViewVertical
        //textViewVertical.setText("2015年");
        titleYear.setText(year);
        titleMonth.setText(month);
    */
        //write display
        buttonWrite = (Button) findViewById(R.id.button_write);
        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteNoteActivity.class);
                startActivity(intent);
            }
        });

    }
    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            dbHelper = new MyDatabaseHelper(MainActivity.this, "NoteStore.db", null, 1);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //查询NOTE表中最近月份的笔记并显示出来，
            //db.rawQuery("select id,year,month from Note where id = 1", null);
            Cursor cursor = db.query("Note", new String[]{"id", "year", "month"},
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
                        //noteItemList.
                    } while (cursor2.moveToNext());
                }
            } else { //若表为空，则显示当前年月
                year = "二零一五年";
                month = "十月";
            }
            cursor.close();
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Typeface customFont = Typeface.createFromAsset(MainActivity.this.getAssets(), "fonts/KangXi.ttf");
            titleYear = (TextViewVertical) findViewById(R.id.title_year);
            //titleYear.setTypeface(customFont);
            titleMonth = (TextViewVertical) findViewById(R.id.title_month);
            //titleMonth.setTypeface(customFont);

            //TextViewVertical
            //textViewVertical.setText("2015年");
            titleYear.setText(year);
            titleMonth.setText(month);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

}
