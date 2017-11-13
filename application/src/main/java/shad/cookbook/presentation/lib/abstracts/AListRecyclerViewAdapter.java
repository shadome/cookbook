package shad.cookbook.presentation.lib.abstracts;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class AListRecyclerViewAdapter<TModel,TView extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<TView> {
    private final List<TModel> mList;

    public AListRecyclerViewAdapter() {
        mList = new ArrayList<>();
    }

    public AListRecyclerViewAdapter(List<TModel> items) {
        mList = items;
    }

    @Override public int getItemCount() {
        return mList.size();
    }

    @Override public abstract TView onCreateViewHolder(ViewGroup parent, int viewType);

    @Override public abstract void onBindViewHolder(final TView holder, int position);

}