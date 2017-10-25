package shad.cookbook.presentation.activities.recipes;

import android.app.Application;

public class CookbookApplication extends Application {
//    private DaoSession mDaoSession;
//
//    public List<RecipeView> recipes = new ArrayList<>();
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mDaoSession = new DaoMaster(
//                new DaoMaster.DevOpenHelper(this, "cookbook.db").getWritableDb()).newSession();
//        // USER CREATION FOR DEMO PURPOSE
////        RecipeEntity r = new RecipeEntity(1L, "name", "description", "tips", "instructions", null);
////        FoodEntity f1 = new FoodEntity(1L, "tofu");
////        FoodEntity f2 = new FoodEntity(2L, "water");
////        IngredientEntity g1 = new IngredientEntity(1L, r.getId(), f1.getId(), "ingredient_1", 200, Unit.G);
////        IngredientEntity g2 = new IngredientEntity(2L, r.getId(), f2.getId(), "ingredient_2", 100, Unit.L);
////        DaoMaster.dropAllTables(mDaoSession.getDatabase(), true);
////        DaoMaster.createAllTables(mDaoSession.getDatabase(), true);
//
////        mDaoSession.getRecipeDao().deleteAll();
////        mDaoSession.getFoodDao().deleteAll();
////        mDaoSession.getIngredientDao().deleteAll();
////        mDaoSession.getRecipeDao().insertOrReplace(r);
////        mDaoSession.getFoodDao().insertOrReplace(f1);
////        mDaoSession.getFoodDao().insertOrReplace(f2);
////        mDaoSession.getIngredientDao().insertOrReplace(g1);
////        mDaoSession.getIngredientDao().insertOrReplace(g2);
//
////        resetRecipes();
//        recipes = RecipeView.Convert(mDaoSession.getRecipeEntityDao().loadAll());
//    }
//
//    private void resetRecipes() {
//        RecipeEntity r;
//        InputStream is1 = this.getApplicationContext().getResources().openRawResource(+R.drawable.wide);
//        InputStream is2 = this.getApplicationContext().getResources().openRawResource(+R.drawable.tall);
//        ByteArrayOutputStream buffer1 = new ByteArrayOutputStream();
//        ByteArrayOutputStream buffer2 = new ByteArrayOutputStream();
//        try {
//            byte[] data = new byte[1024];
//            int nRead;
//            while ((nRead = is1.read(data, 0, data.length)) != -1)
//                buffer1.write(data, 0, nRead);
//            while ((nRead = is2.read(data, 0, data.length)) != -1)
//                buffer2.write(data, 0, nRead);
//        } catch (Exception e) { e.printStackTrace(); }
//        for (int i = 0; i < 10; i++) {
//            r = mDaoSession.getRecipeEntityDao().load((long) i);
//            r.setName("Name " + i);
//            r.setDesc("Description " + i);
//            if (i%2==0)
//                r.setImage(buffer1.toByteArray());
//            else
//                r.setImage(buffer2.toByteArray());
//            mDaoSession.getRecipeEntityDao().insertOrReplace(r);
//        }
//    }
}
