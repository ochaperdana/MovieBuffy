package id.sch.smktelkom_mlg.privateassignment.xirpl125.moviebuffy;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://api.themoviedb.org/3/movie/popular?api_key=1fcf9e5f6d9c565825c3c7435ddfffea";
    public TextView textViewTitle;
    public TextView textViewDesc;
    public TextView textViewRelease;
    public TextView textViewAnother;
    public ImageView imageViewPict;
    public String url;
    private Integer mKey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mKey = getIntent().getExtras().getInt("blog_id");
        loadRecyclerViewData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);*/
            }
        });

        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewRelease = (TextView) findViewById(R.id.textViewRelease);
        textViewDesc = (TextView) findViewById(R.id.textViewDesc);
        textViewAnother = (TextView) findViewById(R.id.textViewAnother);
        imageViewPict = (ImageView) findViewById(R.id.imageViewDetail);

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ab) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(ab);
                            JSONArray array = jsonObject.getJSONArray("results");
                            JSONObject o = array.getJSONObject(mKey);

                            setTitle("");
                            textViewTitle.setText(o.getString("title"));
                            textViewRelease.setText(o.getString("release_date"));
                            textViewDesc.setText(o.getString("overview"));
                            textViewAnother.setText("Vote Avergae" + o.getString("vote_average"));
                            url = o.getJSONObject("link").getString("url");
                            Glide
                                    .with(DetailActivity.this)
                                    .load("http://image.tmdb.org/t/p/w500" + o.getString("poster_path"))
                                    .into(imageViewPict);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                    }

                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
