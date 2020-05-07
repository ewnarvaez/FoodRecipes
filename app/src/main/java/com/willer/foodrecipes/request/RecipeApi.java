package com.willer.foodrecipes.request;

import com.willer.foodrecipes.request.response.RecipeResponse;
import com.willer.foodrecipes.request.response.RecipeSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {

    @GET("api/search")
    Call<RecipeSearchResponse> searchRecipe(
            @Query("q") String query,
            @Query("page") String page
    );

    @GET("api/get")
    Call<RecipeResponse> getRecipe(
            @Query("rId") String recipeId
    );
}
