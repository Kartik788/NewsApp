package com.ringolatechapps.newsera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.SourcesRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.models.response.SourcesResponse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    ArrayList<Arcticle> arrayList;
    EditText searchbar;
    NewsApiClient newsApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.view_pager_id);
        searchbar = findViewById(R.id.search_id);
        arrayList = new ArrayList<>();

        newsApiClient = new NewsApiClient("57415e0220ca4bf4ae9f865957023aa9");
        fetchinitNews();
        searchbar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    close_keyboard();
                    searchbar.clearFocus();
                    fetchNews(searchbar.getText().toString());
                    return true;
                }
                return false;
            }
        });



    }

    private void fetchNews(String category) {

        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(category)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        arrayList.clear();
                        for (int i = 0; i < response.getArticles().size(); i++) {
                            Arcticle arcticle = new Arcticle(response.getArticles().get(i).getUrlToImage(), response.getArticles().get(i).getTitle(), response.getArticles().get(i).getDescription(), response.getArticles().get(i).getContent(), response.getArticles().get(i).getUrl());
                            arrayList.add(arcticle);
                        }
                        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(arrayList, MainActivity.this);
                        viewPager2.setAdapter(viewPagerAdapter);
                        viewPager2.setOffscreenPageLimit(4);


                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void fetchinitNews() {

        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q("science")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        arrayList.clear();
                        for (int i = 0; i < response.getArticles().size(); i++) {
                            Arcticle arcticle = new Arcticle(response.getArticles().get(i).getUrlToImage(), response.getArticles().get(i).getTitle(), response.getArticles().get(i).getDescription(), response.getArticles().get(i).getContent(), response.getArticles().get(i).getUrl());
                            arrayList.add(arcticle);
                        }
                        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(arrayList, MainActivity.this);
                        viewPager2.setAdapter(viewPagerAdapter);
                        viewPager2.setOffscreenPageLimit(4);


                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void close_keyboard() {
        View view = MainActivity.this.getCurrentFocus();
        if(view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}