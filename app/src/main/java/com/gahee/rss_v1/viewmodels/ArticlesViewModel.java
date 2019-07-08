package com.gahee.rss_v1.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class ArticlesViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<List<ArrayList<Article>>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<Article> articles = new ArrayList<>();
    private List<ArrayList<Article>> list = new ArrayList<>();

    public ArticlesViewModel(){
        repository = Repository.getInstance();
    }

    public MutableLiveData<List<ArrayList<Article>>> getMutableLiveData() {
        return repository.getListMutableLiveData();
    }

    //오직 라이브 데이터만 뷰 모델 안에 있어야 하는 것인가?
    public List<ArrayList<Article>> getList() {
        return repository.getList();
    }

    public ArrayList<Article> getArticles() {
        return repository.getArticleArrayList();
    }

    //여기다가 유저가 선택한 토픽의 스트링을 넘겨주어서 정보를 요청해야 한다.
    public void requestArticles(String topic){
        repository.requestDataAsync(topic);
    }

}
