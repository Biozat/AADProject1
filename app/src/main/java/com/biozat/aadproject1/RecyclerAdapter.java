package com.biozat.aadproject1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private final Context mcontext;
    private final List<Leader> mNotes;
    private final LayoutInflater layoutInflater;




    public RecyclerAdapter(Context mcontext, List<Leader> mNotes) {
        this.mcontext = mcontext;
        layoutInflater = LayoutInflater.from(mcontext);
        this.mNotes = mNotes;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.real_list_layout, viewGroup, false);


        return new RecyclerAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Leader note = mNotes.get(i);
       viewHolder.name.setText(note.getName());
       String score = note.score+" learning hours, "+note.country;
       viewHolder.title.setText(score);
        Picasso.get().load(note.getBadgeUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher).into(viewHolder.img);


    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder{

        public final ImageView img;
        public final TextView name,title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            name = (TextView) itemView.findViewById(R.id.name);
            title = (TextView) itemView.findViewById(R.id.title);



        }






    }
}
