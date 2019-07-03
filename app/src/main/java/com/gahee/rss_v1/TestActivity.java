package com.gahee.rss_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gahee.rss_v1.CNN.FeedAPIcnn;
import com.gahee.rss_v1.CNN.FeedCnn;
import com.gahee.rss_v1.CNN.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = TestActivity.class.getSimpleName();
    private static final String BASE_URL = "http://rss.cnn.com/rss/";

    private TextView textView;
    private Button button;
    StringBuffer desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        textView = findViewById(R.id.tv_test);
        button = findViewById(R.id.btn_test);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveSpaceFeed();
            }
        });

    }

    public void retrieveTechFeed(){
        //read url using retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FeedAPIcnn feedAPIcnn = retrofit.create(FeedAPIcnn.class);

        Call<FeedCnn> call = feedAPIcnn.getEditionTechnologyFeed();

        call.enqueue(new Callback<FeedCnn>() {

            @Override
            public void onResponse(Call<FeedCnn> call, Response<FeedCnn> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                Log.d(TAG, "description : " + response.body().getChannel().getItem().get(1).getDescription());

                List<Item> items = response.body().getChannel().getItem();
                desc = new StringBuffer();
                for(int i = 0; i < items.size(); i++){
                    desc.append(i + " : " + response.body().getChannel().getItem().get(i).getDescription() + "\n");

                }

                textView.setText(desc.toString());

            }

            @Override
            public void onFailure(Call<FeedCnn> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });


    }

    public void retrieveSpaceFeed(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FeedAPIcnn feedAPIcnn = retrofit.create(FeedAPIcnn.class);

        Call<FeedCnn> call = feedAPIcnn.getEditionSpaceFeed();

        call.enqueue(new Callback<FeedCnn>() {

            @Override
            public void onResponse(Call<FeedCnn> call, Response<FeedCnn> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                Log.d(TAG, "description : " + response.body().getChannel().getItem().get(1).getDescription());

                List<Item> items = response.body().getChannel().getItem();
                desc = new StringBuffer();
                for(int i = 0; i < items.size(); i++){
                    desc.append(i + " : " + response.body().getChannel().getItem().get(i).getDescription() + "\n");

                }

                textView.setText(desc.toString());

            }

            @Override
            public void onFailure(Call<FeedCnn> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });
    }



}
