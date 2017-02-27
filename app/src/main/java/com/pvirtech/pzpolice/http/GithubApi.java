package com.pvirtech.pzpolice.http;


import com.pvirtech.pzpolice.main.one.CaseQueryEntity;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import rx.Observable;

public interface GithubApi {

    /**
     * See https://developer.github.com/v3/users/
     */
    @GET("AntiFake/test/{repo}")
    Call<ResponseBody> contributorsBySimpleGetCall(@Path("repo") String repo);

    @GET("AntiFake/test/{repo}")
    Observable<ResponseBody> getStirng(@QueryMap Map<String, String> hm);

    @POST("dspws.do?")
    Observable<ResponseBody> ApprovalData(@Query("param-name") String repo);

    /*---------------------------------------------------------------------------------*/
    @POST("getQbcf.do")
    Observable<HttpResult<List<CaseQueryEntity>>> CaseQueryData(@QueryMap Map<String, String> hm);

    @Streaming
    @GET("{repo}")
    Observable<ResponseBody> down(@Path("repo") String repo);

    @POST("test.do")
    Observable<InputStream> test();
}