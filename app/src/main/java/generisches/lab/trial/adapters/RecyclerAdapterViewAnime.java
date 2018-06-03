package generisches.lab.trial.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import generisches.lab.trial.R;

import java.util.List;

import generisches.lab.trial.R;
import generisches.lab.trial.activities.AnimeActivity;
import generisches.lab.trial.model.Anime;

public class RecyclerAdapterViewAnime extends RecyclerView.Adapter<RecyclerAdapterViewAnime.MyViewHolder>{
    private Context mContext;
    private List<Anime> mData;
    RequestOptions option;

    public RecyclerAdapterViewAnime(Context context, List<Anime> data) {
        mContext = context;
        mData = data;

        //Request Option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape_anime).error(R.drawable.loading_shape_anime);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_item, parent, false);

        //For Content Desc
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, AnimeActivity.class);
                i.putExtra("anime_name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("anime_description",mData.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("anime_studio",mData.get(viewHolder.getAdapterPosition()).getStudio());
                i.putExtra("anime_category",mData.get(viewHolder.getAdapterPosition()).getCategorie());
                i.putExtra("anime_nb_episode",mData.get(viewHolder.getAdapterPosition()).getNb_episode());
                i.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getImage_url());

                mContext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_category.setText(mData.get(position).getCategorie());
        holder.tv_rati.setText(mData.get(position).getRating());
        holder.tv_studio.setText(mData.get(position).getStudio());

        //Load img from internet and set it into Imageview using Glide
        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.img_anim_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_rati, tv_studio, tv_category;
        ImageView img_anim_thumbnail;
        //After naming item as container
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.anime_name);
            tv_rati = itemView.findViewById(R.id.anime_rating);
            tv_studio = itemView.findViewById(R.id.studio);
            tv_category = itemView.findViewById(R.id.categorie);
            img_anim_thumbnail = itemView.findViewById(R.id.thumbnail_anime);

            view_container = itemView.findViewById(R.id.animcontainer);
        }
    }
}
