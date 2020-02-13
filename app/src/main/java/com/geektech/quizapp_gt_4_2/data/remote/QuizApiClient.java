package com.geektech.quizapp_gt_4_2.data.remote;

import android.util.Log;

import com.geektech.core.CoreCallback;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private TriviaApi client = retrofit.create(TriviaApi.class);

    @Override
    public void getQuestions(final QuestionsCallback callback) {
        Call<QuizQuestionsResponse> call = client.getQuestions(
                10,
                null,
                "hard"
        );

        Log.d("ololo", "URL - " + call.request().url());

        call.enqueue(new CoreCallback<QuizQuestionsResponse>() {
            @Override
            public void onSuccess(QuizQuestionsResponse result) {
                callback.onSuccess(result.getResults());
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }

    private interface TriviaApi {
        @GET("api.php")
        Call<QuizQuestionsResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") String category,
                @Query("difficulty") String difficulty
        );
    }
}
