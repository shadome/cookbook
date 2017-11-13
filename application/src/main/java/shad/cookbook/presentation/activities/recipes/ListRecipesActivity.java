package shad.cookbook.presentation.activities.recipes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import shad.cookbook.R;

public class ListRecipesActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.list_recipes_activity);
//        Fragment f = new ListRecipesFragment();
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.list_recipes_fragment_main, f);
//        fragmentTransaction.commit();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.list_recipes_fragment_main, new ListRecipesFragment());
        ft.commit();
    }
}
