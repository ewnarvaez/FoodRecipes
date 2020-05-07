package com.willer.foodrecipes;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.willer.foodrecipes.model.Recipe;
import com.willer.foodrecipes.request.RecipeApi;
import com.willer.foodrecipes.request.ServiceGenerator;
import com.willer.foodrecipes.request.response.RecipeResponse;
import com.willer.foodrecipes.request.response.RecipeSearchResponse;
import com.willer.foodrecipes.viewmodel.RecipeListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {

    private static final String TAG = "RecipeListActivity";

    // var
    private RecipeListViewModel mRecipeListViewModel;
    private RecipeApi mRecipeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        mRecipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);
        mRecipeApi = ServiceGenerator.getRecipeApi();

        subscribeObservers();

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testSingleRetrofitRequest();
            }
        });
    }

    private void subscribeObservers(){
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {

            }
        });
    }

    private void testRetrofitRequest(){
        Call<RecipeSearchResponse> responseCall = mRecipeApi.searchRecipe(
                "Chicken breast",
                "1"
        );

        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                if (response.code() == 200){
                    Log.d(TAG, "onResponse: response raw" + response.toString());
                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
                    for (Recipe recipe: recipes){
                        Log.d(TAG, "onResponse: Response title: " + recipe.getRecipeId());
                    }
                }
                else {
                    Log.e(TAG, "onResponse: " + response.errorBody().toString() );
                }
            }

            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void testSingleRetrofitRequest(){
        Call<RecipeResponse> responseCall = mRecipeApi.getRecipe(
                "30372"
        );

        responseCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.code() == 200){
                    Log.d(TAG, "onResponse: 200 Ok: " + response.body().toString());

                }
                else {
                    Log.e(TAG, "onResponse: Error " + response.errorBody().toString() );

                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                t.getMessage();
            }
        });
    }

}
