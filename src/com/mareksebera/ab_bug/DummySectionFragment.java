package com.mareksebera.ab_bug;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.TextView;

public class DummySectionFragment extends Fragment {

	public static final String ARG_SECTION_NUMBER = "section_number";
	public static final int MENU_SEARCH = -1;
	protected MenuItem searchItem;
	protected SearchView mSearchView;

	public DummySectionFragment() {
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		mSearchView = new SearchView(getActivity().getActionBar()
				.getThemedContext());
		searchItem = menu
				.add(Menu.NONE, MENU_SEARCH, Menu.NONE, "Search")
				.setIcon(android.R.drawable.ic_menu_search)
				.setActionView(mSearchView)
				.setShowAsActionFlags(
						MenuItem.SHOW_AS_ACTION_ALWAYS);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		TextView textView = new TextView(getActivity());
		textView.setGravity(Gravity.CENTER);
		textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
		return textView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		ActionBar ab = getActivity().getActionBar();
		int mode = 0;
		switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
		default:
		case 1:
			mode = ActionBar.NAVIGATION_MODE_LIST;
			ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
					getActivity(),
					android.R.layout.simple_spinner_dropdown_item,
					new String[] { "A", "B", "C" });
			ab.setListNavigationCallbacks(spinnerArrayAdapter,
					new OnNavigationListener() {

						@Override
						public boolean onNavigationItemSelected(
								int itemPosition, long itemId) {
							return false;
						}
					});
			break;
		case 2:
		case 3:
			mode = ActionBar.NAVIGATION_MODE_STANDARD;
			break;
		}
		getActivity().getActionBar().setNavigationMode(mode);
	}
}