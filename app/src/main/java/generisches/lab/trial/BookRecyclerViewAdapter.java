package generisches.lab.trial;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.BookViewHolder>{

    private Context mContext;
    private List<Book> mData;

    public BookRecyclerViewAdapter(Context context, List<Book> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_book, parent, false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, final int position) {
        holder.tv_book_title.setText(mData.get(position).getBookTitle());
        holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());

        //Set click listener
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, BookDetailActivity.class);
                i.putExtra("BookTitle", mData.get(position).getBookTitle());
                i.putExtra("BookDescription", mData.get(position).getDescription());
                i.putExtra("BookThumbnail", mData.get(position).getThumbnail());
                i.putExtra("BookCategory", mData.get(position).getCategory());
                mContext.startActivity(i)   ;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder{

        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cv;

        public BookViewHolder(View itemView) {
            super(itemView);
            tv_book_title = itemView.findViewById(R.id.book_title_id);
            img_book_thumbnail = itemView.findViewById(R.id.book_img_id);
            cv = itemView.findViewById(R.id.cardview_id);
        }
    }
}
