package com.gahee.rss_v1.remoteDataSource;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gahee.rss_v1.CNN.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ViewModelRemote extends ViewModel {

    private RepositoryRemote repositoryRemote;
    private MutableLiveData<List<ArrayList<Article>>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<Article> articles = new ArrayList<>();
    private List<ArrayList<Article>> list = new ArrayList<>();

    public ViewModelRemote(){
        repositoryRemote = RepositoryRemote.getInstance();
    }

    public MutableLiveData<List<ArrayList<Article>>> getMutableLiveData() {
        return repositoryRemote.getListMutableLiveData();
    }

    //오직 라이브 데이터만 뷰 모델 안에 있어야 하는 것인가?
    public List<ArrayList<Article>> getList() {
        return repositoryRemote.getList();
    }

    public ArrayList<Article> getArticles() {
        return repositoryRemote.getArticleArrayList();
    }

    //여기다가 유저가 선택한 토픽의 스트링을 넘겨주어서 정보를 요청해야 한다.
    public void requestArticles(String topic){
        repositoryRemote.requestDataAsync(topic);
    }

}
