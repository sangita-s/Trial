package generisches.lab.trial.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import generisches.lab.trial.R;
import generisches.lab.trial.adapters.RecyclerAdapterViewAnime;
import generisches.lab.trial.model.Anime;

public class Wifi extends AppCompatActivity {

    private final String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
    private JsonArrayRequest mRequest;
    private RequestQueue mRequestQueue;
    private List<Anime> lstAnime;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        lstAnime = new ArrayList<>();
        mRecyclerView = findViewById(R.id.anime_recycler);
        jsonrequest();
    }

    private void jsonrequest() {
        mRequest = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for(int i = 0; i < response.length();i++){
                    try{
                        jsonObject = response.getJSONObject(i);
                        Anime anime = new Anime();
                        anime.setName(jsonObject.getString("name"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setCategorie(jsonObject.getString("categorie"));
                        anime.setNb_episode(jsonObject.getInt("episode"));
                        anime.setStudio(jsonObject.getString("studio"));
                        anime.setImage_url(jsonObject.getString("img"));

                        lstAnime.add(anime);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    setupRecyclerView(lstAnime);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue = Volley.newRequestQueue(Wifi.this);
        mRequestQueue.add(mRequest);

    }

    private void setupRecyclerView(List<Anime> lstAnime) {
        RecyclerAdapterViewAnime myAdapter = new RecyclerAdapterViewAnime(this, lstAnime);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(myAdapter);
    }
}
