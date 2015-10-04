package com.mycompany.tinynote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by lishaowei on 15/10/2.
 */
public class NotesItemAdapter extends ArrayAdapter<NotesItem> {

    private int resourceId;

    public NotesItemAdapter(Context context, int textViewResouceId,
                            List<NotesItem> objects) {
        super(context, textViewResouceId, objects);
        resourceId = textViewResouceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NotesItem notesItem = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextViewVertical notesTitle = (TextViewVertical) view.findViewById(R.id.note_item);
        notesTitle.setText(notesItem.getTitle());
        return view;
    }
}
