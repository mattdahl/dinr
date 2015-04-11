package edu.pomona.dinr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
	private String[] mDataset;
	private Activity a;

	// Provide a reference to the views for each data item
	// Complex data items may need more than one view per item, and
	// you provide access to all the views for a data item in a view holder
	public static class ViewHolder extends RecyclerView.ViewHolder {
		View mView;
		// each data item is just a string in this case
		public ViewHolder(View v) {
			super(v);
			mView = v;
		}
	}

	// Provide a suitable constructor (depends on the kind of dataset)
	public MatchesAdapter(String[] myDataset, Activity a) {
		mDataset = myDataset;
		this.a = a;
	}

	// Create new views (invoked by the layout manager)
	@Override
	public MatchesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		// create a new view
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.contact_card, parent, false);
		Button contact = (Button) v.findViewById(R.id.contact);
		contact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(a);
				// Get the layout inflater
				LayoutInflater inflater = a.getLayoutInflater();

				// Inflate and set the layout for the dialog
				// Pass null as the parent view because its going in the dialog layout
				builder.setView(inflater.inflate(R.layout.contact_dialog, null))
				// Add action buttons
				.setPositiveButton("Send", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						Toast.makeText(a, "Message sent!", Toast.LENGTH_SHORT).show();
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});
				builder.create().show();
			}
		});
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		// - get element from your dataset at this position
		// - replace the contents of the view with that element
		/* TODO: populate with real data */
	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		return mDataset.length;
	}
}
