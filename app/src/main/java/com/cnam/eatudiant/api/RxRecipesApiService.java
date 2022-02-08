package com.cnam.eatudiant.api;

import com.cnam.eatudiant.data.Response;
import com.cnam.eatudiant.data.recipe.RecipesResponse;
import com.cnam.eatudiant.data.recipeDetails.Fullrecipe;
import com.cnam.eatudiant.data.recipeDetails.StepBody;
import com.cnam.eatudiant.data.recipeDetails.StepsResponse;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RxRecipesApiService {

    @POST("recette/getListRecette")
    Observable<Response<RecipesResponse>> getRecipes();

    @POST("/recette/getRecette")
    Observable<Response<Fullrecipe>> getFullRecipe(@Body StepBody stepBody);

    @POST("/recette/getEtapesRecette")
    Observable<Response<StepsResponse>> getRecipeStep(@Body StepBody stepBody);

}
