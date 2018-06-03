package generisches.lab.trial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetailActivity extends AppCompatActivity {

    private TextView tv_bookTitle, tv_bookDesc, tv_cat;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        tv_bookTitle = findViewById(R.id.detail_book_name);
        tv_bookDesc = findViewById(R.id.detail_book_desc);
        tv_cat = findViewById(R.id.detail_book_cate);
        img = findViewById(R.id.bookthumbnaildetail);

        Intent i = getIntent();
        String title = i.getStringExtra("BookTitle");
        String desc = i.getStringExtra("BookDescription");
        int image = i.getExtras().getInt("BookThumbnail");
        String catego = i.getStringExtra("BookCategory");

        tv_bookTitle.setText(title);
        tv_bookDesc.setText(desc);
        tv_cat.setText(catego);
        img.setImageResource(image);
    }
}
