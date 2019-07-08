package com.gahee.rss_v1.requests;

import com.gahee.rss_v1.CNN.CnnAPI;
import com.gahee.rss_v1.helpers.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


/**
 *Singleton class
 * builds the retrofit object and returns CnnAPI object.
 */
public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static CnnAPI cnnAPI = retrofit.create(CnnAPI.class);

    public static CnnAPI getCnnAPI(){
        return cnnAPI;
    }

}
