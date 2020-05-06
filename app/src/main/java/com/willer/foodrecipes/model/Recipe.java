package com.willer.foodrecipes.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Recipe implements Parcelable {

    @SerializedName("recipe_id")
    private String recipeId;
    private String title;
    private String publisher;
    private String[] ingredients;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("social_rank")
    private float socialRank;

    public Recipe() {
    }

    public Recipe(String recipeId, String title, String publisher, String[] ingredients,
                  String imageUrl, float socialRank) {
        this.recipeId = recipeId;
        this.title = title;
        this.publisher = publisher;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
        this.socialRank = socialRank;
    }

    protected Recipe(Parcel in) {
        recipeId = in.readString();
        title = in.readString();
        publisher = in.readString();
        ingredients = in.createStringArray();
        imageUrl = in.readString();
        socialRank = in.readFloat();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getSocialRank() {
        return socialRank;
    }

    public void setSocialRank(float socialRank) {
        this.socialRank = socialRank;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId='" + recipeId + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                ", imageUrl='" + imageUrl + '\'' +
                ", socialRank=" + socialRank +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeId);
        dest.writeString(title);
        dest.writeString(publisher);
        dest.writeStringArray(ingredients);
        dest.writeString(imageUrl);
        dest.writeFloat(socialRank);
    }
}
