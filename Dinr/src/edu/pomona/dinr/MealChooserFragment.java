package edu.pomona.dinr;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link MealChooserFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link MealChooserFragment#newInstance} factory method to create an instance
 * of this fragment.
 *
 */
public class MealChooserFragment extends Fragment {
	private OnFragmentInteractionListener mListener;
	
	private boolean[] selected;
	
	private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 *
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment MealChooserFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static MealChooserFragment newInstance() {
		MealChooserFragment fragment = new MealChooserFragment();
		return fragment;
	}

	public MealChooserFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		selected = new boolean[3];
		
		SharedPreferences mealPrefs = getActivity().getSharedPreferences("meals", Context.MODE_PRIVATE);
		for (int i = 0; i < selected.length; i++) {
			selected[i] = mealPrefs.getBoolean("meal" + i, false);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_meal_chooser, container, false);
		mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
		mRecyclerView.setHasFixedSize(true);
		
		mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        
        RecyclerAdapter a = new RecyclerAdapter(getActivity().getSupportFragmentManager());
        mRecyclerView.setAdapter(a);
		
		return v;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

}
