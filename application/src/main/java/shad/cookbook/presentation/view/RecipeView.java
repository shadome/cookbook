package shad.cookbook.presentation.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

import shad.cookbook.data.model.entities.RecipeEntity;

public class RecipeView {

    private RecipeEntity mSource;

    private Bitmap mImageBitmap;

    private RecipeView(RecipeEntity source) {
        this.mSource = source;
    }

    public Bitmap getImageBitmap() {
        if (mImageBitmap == null)
            mImageBitmap = BitmapFactory.decodeByteArray(mSource.getImage(), 0, mSource.getImage().length);
        return mImageBitmap;
    }

    public static List<RecipeView> Convert(List<RecipeEntity> entities) {
//        return entities.stream().map(RecipeView::new).collect(Collectors.toList());
        List<RecipeView> recipes = new ArrayList<>();
        for (RecipeEntity re : entities)
            recipes.add(new RecipeView(re));
        return recipes;
    }

    public RecipeEntity getSource() {
        return this.mSource;
    }

    public class ByteToBitmapRunnable implements Runnable {
        byte[] mSrc;
        Bitmap mRes;
        public ByteToBitmapRunnable (byte[] src, Bitmap res) {
            mSrc = src;
            mRes = res;
        }
        @Override public void run() {
            mRes = BitmapFactory.decodeByteArray(mSrc, 0, mSrc.length);
        }
    }
}
