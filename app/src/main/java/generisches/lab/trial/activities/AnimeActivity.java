package generisches.lab.trial.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import generisches.lab.trial.R;

public class AnimeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        //Hide default actionbar
        getSupportActionBar().hide();

        //Receive Data
        String name = getIntent().getExtras().getString("anime_name");
        String description = getIntent().getExtras().getString("anime_description");
        String studio = getIntent().getExtras().getString("anime_studio");
        String category = getIntent().getExtras().getString("anime_category");
        String image_url = getIntent().getExtras().getString("anime_img");
        int nb_episode = getIntent().getExtras().getInt("anime_nb_episode");
        String rating = getIntent().getExtras().getString("anime_rating");

        //ini views
        CollapsingToolbarLayout c = findViewById(R.id.anim_collapsing);
        c.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.aa_anime_name);
        TextView tv_studio = findViewById(R.id.aa_studio);
        TextView tv_category = findViewById(R.id.aa_categorie);
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating = findViewById(R.id.aa_anime_rating);
        ImageView img = findViewById(R.id.aa_thumbnail_anime);

        //Setting values to each view
        tv_name.setText(name);
        tv_category.setText(category);
        tv_studio.setText(studio);
        tv_rating.setText(rating);
        tv_description.setText(description);

        c.setTitle(name);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape_anime).error(R.drawable.loading_shape_anime);

        //set img using glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);

    }
}
