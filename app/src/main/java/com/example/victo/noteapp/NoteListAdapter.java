package com.example.victo.noteapp;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {


    private final LayoutInflater mInflater;
    private List<NoteEntry> mNotes;

    NoteListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {

        NoteEntry current = mNotes.get(i);
        noteViewHolder.noteItemView.setText(current.getNote());

    }

  public void setNotes(List<NoteEntry> notes) {
        mNotes =  notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNotes != null)
        return mNotes.size();
        else return 0;
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteItemView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteItemView = itemView.findViewById(R.id.textView);
        }
    }
}
