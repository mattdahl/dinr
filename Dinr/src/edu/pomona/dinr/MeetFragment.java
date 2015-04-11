package edu.pomona.dinr;

import com.andtinder.model.CardModel;
import com.andtinder.model.Orientations.Orientation;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link MeetFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link MeetFragment#newInstance} factory method to create an instance of this
 * fragment.
 *
 */
public class MeetFragment extends Fragment {
	private OnFragmentInteractionListener mListener;
	private CardContainer c;

	// TODO: Rename and change types and number of parameters
	public static MeetFragment newInstance() {
		MeetFragment fragment = new MeetFragment();
		return fragment;
	}

	public MeetFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_meet, container, false);
		c = (CardContainer) v.findViewById(R.id.profile_stack);
		c.setOrientation(Orientation.Ordered);
		SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this.getActivity());
		adapter.add(new CardModel("Title1", "Description goes here", getActivity().getResources().getDrawable(R.drawable.zachhauser)));
		c.setAdapter(adapter);
		return v;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	/*@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}*/

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
