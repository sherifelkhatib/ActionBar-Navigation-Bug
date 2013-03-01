package com.mareksebera.ab_bug;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends FragmentActivity implements OnClickListener{

	public static int currentLevel = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pushNewOne();
	}

	public void pushNewOne() {
		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, ++currentLevel);
		fragment.setArguments(args);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.content, fragment, String.valueOf(currentLevel));
		ft.addToBackStack(String.valueOf(currentLevel));
		ft.commit();
	}

	public void onClick(View v) {
		pushNewOne();
	}

	@Override
	public void onBackPressed() {
		--currentLevel;
		if(currentLevel!=0)
			super.onBackPressed();
		else
			finish();
	}
}
