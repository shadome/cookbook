package shad.cookbook.presentation.activities.recipes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shad.cookbook.CookBookApplication;
import shad.cookbook.R;
import shad.cookbook.presentation.view.RecipeView;

public class ListRecipesFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_recipes_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.list_recipes_fragment_recyclerview);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);
        mRecyclerView.setAdapter(new ListRecipesFragmentRecyclerViewAdapter(((CookBookApplication) getActivity().getApplication()).recipes));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public class ListRecipesFragmentRecyclerViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIconView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mExpandPH;
        public final GridLayout mExpandedLinearLayout1;
        public RecipeView mItem;

        public ListRecipesFragmentRecyclerViewHolder(View view) {
            super(view);
            mView = view;
            //mCardView = view.findViewById(R.id.cv);
            mIconView = view.findViewById(R.id.icon);
            mIdView = view.findViewById(R.id.id);
            mContentView = view.findViewById(R.id.content);
            mExpandPH = view.findViewById(R.id.expand_ph);
            mExpandedLinearLayout1 = view.findViewById(R.id.expandedLinearLayout1);
        }
    }

    public class ListRecipesFragmentRecyclerViewAdapter extends RecyclerView.Adapter<ListRecipesFragmentRecyclerViewHolder> {
        List<RecipeView> mItems;
        List<Bitmap> mBitmaps;

        public ListRecipesFragmentRecyclerViewAdapter(List<RecipeView> items) {
            mItems = items;
            mBitmaps = new ArrayList<>();
            for (RecipeView item : items)
                mBitmaps.add(item.getImageBitmap());
        }

        @Override public int getItemCount() { return mItems.size(); }

        @Override public ListRecipesFragmentRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ListRecipesFragmentRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recipes_item, parent, false));
        }

        @Override public void onBindViewHolder(final ListRecipesFragmentRecyclerViewHolder holder, int position) {
            if (holder.mItem != null) return;
            holder.mItem = mItems.get(position);
            holder.mIdView.setText(mItems.get(position).getSource().getName());
            holder.mContentView.setText(mItems.get(position).getSource().getDesc());
            holder.mExpandedLinearLayout1.removeAllViewsInLayout();
            holder.mExpandedLinearLayout1.setColumnCount(2);
            holder.mExpandedLinearLayout1.setRowCount(10);
            LinearLayout ll1 = new LinearLayout(getActivity().getApplicationContext());
            ll1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.5f));
            LinearLayout ll2 = new LinearLayout(getActivity().getApplicationContext());
            ll2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.5f));
            for (int i = 0; i < 10; i++) {
                TextView tv = new TextView(getActivity().getApplicationContext());
                tv.setMaxLines(1);
                tv.setText(String.format("IngredientEntity %s", i));
                tv.setGravity(Gravity.FILL_HORIZONTAL);
                CheckBox cb = new CheckBox(getActivity().getApplicationContext());
                cb.setChecked(true);
                cb.setLabelFor(tv.getId());
                holder.mExpandedLinearLayout1.addView(cb);
                holder.mExpandedLinearLayout1.addView(tv);
            }
//
//            for (int i = 0; i < 10; i++) {
//                LinearLayout l = new LinearLayout(getActivity().getApplicationContext());
//                l.setOrientation(LinearLayout.HORIZONTAL);
//                ImageButton ib = new ImageButton(getActivity().getApplicationContext());
//                ib.setColorFilter(Color.LTGRAY);
//                ib.setImageResource(R.drawable.ic_check_box_white_24dp);
//                ib.setAdjustViewBounds(true);
//                int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics());
//                ib.setMaxHeight(dp);
//                ib.setMaxWidth(dp);
//                ib.setColorFilter(Color.LTGRAY);
//                TextView tv = new TextView(getActivity().getApplicationContext());
//                tv.setMaxLines(1);
//                tv.setText(String.format("IngredientEntity %s", i));
//                l.addView(ib);
//                l.addView(tv);
//                l.setGravity(Gravity.CENTER_VERTICAL);
//                holder.mExpandedLinearLayout1.addView(l);
//                holder.mExpandedLinearLayout1.setVisibility(View.GONE);
//            }
            holder.mIconView.setImageBitmap(mBitmaps.get(position));
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.mExpandPH.setColorFilter(Color.DKGRAY);
                    if (holder.mExpandedLinearLayout1.getVisibility() == View.GONE) {
                        holder.mExpandedLinearLayout1.setVisibility(View.VISIBLE);
                        holder.mExpandPH.setImageResource(R.drawable.ic_expand_more_white_24dp);
                    } else {
                        holder.mExpandedLinearLayout1.setVisibility(View.GONE);
                        holder.mExpandPH.setImageResource(R.drawable.ic_expand_less_white_24dp);
                    }
                }
            });
            holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Bundle b = new Bundle();
                    b.putFloat("id", holder.mItem.getSource().getId());
                    Intent intent = new Intent(ListRecipesFragment.this.getActivity(), DetailRecipeActivity.class);
                    intent.putExtras(b);
                    startActivity(intent);
                    return true;
                }
            });
        }
    }

//    public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
//
//        private final List<RecipeView> mValues;
//
//        public SimpleItemRecyclerViewAdapter(List<RecipeView> items) {
//            mValues = items;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recipes_item, parent, false));
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mItem = mValues.get(position);
//            holder.mIdView.setText(mValues.get(position).getSource().getName());
//            holder.mContentView.setText(mValues.get(position).getSource().getDesc());
//
////            for (IngredientEntity i : holder.mItem.getIngredients()) {
////                LinearLayout l = new LinearLayout(getApplicationContext());
////                l.setOrientation(LinearLayout.HORIZONTAL);
////                ImageView iv = new ImageView(getApplicationContext());
//////                getResources().getDrawable(R.drawable.btn_radio);
//////                iv.setImageResource(R.drawable.btn_check_on_disabled_focused_holo_light);
////                iv.setImageResource(R.drawable.abc_list_selector_holo_light);
////                TextView tv = new TextView(getApplicationContext());
////                tv.setMaxLines(1);
////                tv.setText(i.getAmount() + i.getUnit().getText() + " " + i.getName());
////                l.addView(iv);
////                l.addView(tv);
////                holder.mExpandedLinearLayout1.addView(l);
////            }
//            holder.mExpandedLinearLayout1.removeAllViewsInLayout();
//            for (int i = 0; i < 10; i++) {
//                LinearLayout l = new LinearLayout(getActivity().getApplicationContext());
//                l.setOrientation(LinearLayout.HORIZONTAL);
//                ImageView iv = new ImageView(getActivity().getApplicationContext());
//                iv.setColorFilter(Color.LTGRAY);
//                iv.setImageResource(R.drawable.ic_check_box_white_24dp);
//                iv.setAdjustViewBounds(true);
//                int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
//                iv.setMaxHeight(dp);
//                iv.setMaxWidth(dp);
//                iv.setColorFilter(Color.LTGRAY);
//                TextView tv = new TextView(getActivity().getApplicationContext());
//                tv.setMaxLines(1);
//                tv.setText(String.format("IngredientEntity %s", i));
//                l.addView(iv);
//                l.addView(tv);
//                l.setGravity(Gravity.CENTER_VERTICAL);
//                holder.mExpandedLinearLayout1.addView(l);
//                holder.mExpandedLinearLayout1.setVisibility(View.GONE);
//            }
////            tv.setTextAppearance(-R.attr.textAppearanceListItem);
////            holder.mExpandedContent.setText("Grosse grosse description !!");
////            holder.mExpandedContent.append("\nGrosse grosse description !!");
////            holder.mExpandedContent.append("\nGrosse grosse description !!");
////            holder.mExpandedContent.append("\nGrosse grosse description !!");
////            holder.mExpandedContent.append("\nGrosse grosse description !!");
////            holder.mExpandedContent.append("\nGrosse grosse description !!");
////            byte[] image = mValues.get(position).getImage();
//////            holder.mIconView.setBackgroundColor(Color.BLUE);
////
//////            DisplayMetrics dm = new DisplayMetrics();
//////            getWindowManager().getDefaultDisplay().getMetrics(dm);
//////            int width = dm.widthPixels / 3;
////
//            holder.mIconView.setImageBitmap(mValues.get(position).getImageBitmap());
////                    Bitmap.createScaledBitmap(
////                            BitmapFactory.decodeByteArray(image, 0, image.length)
////                    , 100, 100, true)
////            );
//
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    if (holder.mExpandedContent.getVisibility() == View.GONE)
//                    holder.mExpandedLinearLayout1.setVisibility(
//                            (holder.mExpandedLinearLayout1.getVisibility() == View.GONE) ?
//                                    View.VISIBLE : View.GONE);
////                    ObjectAnimator animation = ObjectAnimator.ofInt(holder.mExpandedContent, "maxLines", holder.mExpandedContent.getMaxLines());
////                    animation.setDuration(5000).start();
////                    else
////                        holder.mExpandedContent.setVisibility(View.GONE);
////                    Intent intent = new Intent(v.getContext(), ItemDetailActivity.class);
////                    intent.putExtra(ItemDetailActivity.class.getName(), holder.getAdapterPosition());
////                    v.getContext().startActivity(intent);
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return mValues.size();
//        }
//    }
}
