package edu.pomona.dinr;

import android.animation.ValueAnimator;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Recycler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
	private String[] mDataset;
	private FragmentManager f;

	// Provide a reference to the views for each data item
	// Complex data items may need more than one view per item, and
	// you provide access to all the views for a data item in a view holder
	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		// each data item is just a string in this case
		public View view;
		private int mOriginalHeight = 0;
		private boolean mIsViewExpanded = false;

		public ViewHolder(View v) {
			super(v);
			view = v;
			v.setOnClickListener(this);
		}

		@Override
		public void onClick(final View view) {
			if (mOriginalHeight == 0) {
				mOriginalHeight = view.getHeight();
			}
			ValueAnimator valueAnimator;
			if (!mIsViewExpanded) {
				mIsViewExpanded = true;
				valueAnimator = ValueAnimator.ofInt(mOriginalHeight, mOriginalHeight + (int) (mOriginalHeight * 2.75));
			} else {
				mIsViewExpanded = false;
				valueAnimator = ValueAnimator.ofInt(mOriginalHeight + (int) (mOriginalHeight * 2.75), mOriginalHeight);
			}
			valueAnimator.setDuration(300);
			valueAnimator.setInterpolator(new LinearInterpolator());
			valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				public void onAnimationUpdate(ValueAnimator animation) {
					Integer value = (Integer) animation.getAnimatedValue();
					view.getLayoutParams().height = value.intValue();
					view.requestLayout();
				}
			});
			valueAnimator.start();

			
		}
	}

	// Provide a suitable constructor (depends on the kind of dataset)
	public RecyclerAdapter(FragmentManager f) {
		mDataset = new String[]{"Breakfast", "Lunch", "Dinner"};
		this.f = f;
	}

	// Create new views (invoked by the layout manager)
	@Override
	public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		// create a new view
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.list_item_meal_type, parent, false);
		// set the view's size, margins, paddings and layout parameters
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		// - get element from your dataset at this position
		// - replace the contents of the view with that element
		TextView t = (TextView) holder.view.findViewById(R.id.meal_name);
		t.setText(mDataset[position]);
	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		return mDataset.length;
	}
}