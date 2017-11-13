package shad.cookbook;

import android.app.Application;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import shad.cookbook.data.model.entities.DaoMaster;
import shad.cookbook.data.model.entities.DaoSession;
import shad.cookbook.data.model.entities.FoodEntity;
import shad.cookbook.data.model.entities.IngredientEntity;
import shad.cookbook.data.model.entities.RecipeEntity;
import shad.cookbook.data.model.entities.UnitEntity;
import shad.cookbook.presentation.view.RecipeView;


public class CookBookApplication extends Application {
    private DaoSession mDaoSession;

    public List<RecipeView> recipes = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "cookbook.db").getWritableDb()).newSession();
        // USER CREATION FOR DEMO PURPOSE
        FoodEntity f1 = new FoodEntity(1L, "tofu");
        FoodEntity f2 = new FoodEntity(2L, "water");
        UnitEntity u1 = new UnitEntity(1L, "g", "grammes");
        UnitEntity u2 = new UnitEntity(2L, "l", "litres");
        IngredientEntity g1 = new IngredientEntity(1L, 1L, f1.getId(), "ingredient_1", 200, 1L);
        IngredientEntity g2 = new IngredientEntity(2L, 1L, f2.getId(), "ingredient_2", 100, 2L);

        DaoMaster.dropAllTables(mDaoSession.getDatabase(), true);
        DaoMaster.createAllTables(mDaoSession.getDatabase(), true);

        mDaoSession.getUnitEntityDao().deleteAll();
        mDaoSession.getRecipeEntityDao().deleteAll();
        mDaoSession.getFoodEntityDao().deleteAll();
        mDaoSession.getIngredientEntityDao().deleteAll();

        mDaoSession.getUnitEntityDao().insertOrReplace(u1);
        mDaoSession.getUnitEntityDao().insertOrReplace(u2);
        for (long l = 0; l < 10; l++) {
            RecipeEntity r = new RecipeEntity(l, "name", "description", "tips", "instructions", null);
            mDaoSession.getRecipeEntityDao().insertOrReplace(r);
        }
        mDaoSession.getFoodEntityDao().insertOrReplace(f1);
        mDaoSession.getFoodEntityDao().insertOrReplace(f2);
        mDaoSession.getIngredientEntityDao().insertOrReplace(g1);
        mDaoSession.getIngredientEntityDao().insertOrReplace(g2);

        resetRecipes();
        recipes = RecipeView.Convert(mDaoSession.getRecipeEntityDao().loadAll());
    }

    private void resetRecipes() {
        RecipeEntity r;
        InputStream is1 = this.getApplicationContext().getResources().openRawResource(+R.drawable.wide);
        InputStream is2 = this.getApplicationContext().getResources().openRawResource(+R.drawable.tall);
        ByteArrayOutputStream buffer1 = new ByteArrayOutputStream();
        ByteArrayOutputStream buffer2 = new ByteArrayOutputStream();
        try {
            byte[] data = new byte[1024];
            int nRead;
            while ((nRead = is1.read(data, 0, data.length)) != -1)
                buffer1.write(data, 0, nRead);
            while ((nRead = is2.read(data, 0, data.length)) != -1)
                buffer2.write(data, 0, nRead);
        } catch (Exception e) { e.printStackTrace(); }
        for (int i = 0; i < 10; i++) {
            r = mDaoSession.getRecipeEntityDao().load((long) i);
            r.setName("Name " + i);
            r.setDesc("Description " + i);
            if (i%2==0)
                r.setImage(buffer1.toByteArray());
            else
                r.setImage(buffer2.toByteArray());
            mDaoSession.getRecipeEntityDao().insertOrReplace(r);
        }
    }
}
