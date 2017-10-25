package shad.cookbook.presentation.activities.recipes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import shad.cookbook.R;

public class ListRecipesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.list_recipes_activity);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.recipe_list_fragment_placeholder, new ListRecipesFragment());
//        ft.commit();
    }
}
