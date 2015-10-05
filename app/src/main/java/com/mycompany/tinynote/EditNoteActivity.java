package com.mycompany.tinynote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by lishaowei on 15/10/5.
 */
public class EditNoteActivity extends Activity {

    private Button buttonModify;
    private Button buttonSave;
    private Button buttonDelete;
    private TextViewVertical noteEditLocation;
    private TextViewVertical noteEditContent;
    private TextViewVertical noteEditTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note);

        // 查询数据库得到日记项目 TO DO

        // 修改按钮
        buttonModify = (Button) findViewById(R.id.edit);
        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditNoteActivity.this, WriteNoteActivity.class);
                startActivity(intent);
            }
        });
        // 保持日记按钮
        buttonSave = (Button) findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // 删除日记按钮
        buttonDelete = (Button) findViewById(R.id.delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
