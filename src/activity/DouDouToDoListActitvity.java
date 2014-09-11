package activity;


import util.DouDouToDoListUtils;
import view.CompleteToDoView;
import view.EditItemView;
import view.NavigationDrawerFragment;
import view.TodayToDoView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oldoldb.doudoutodolist.R;

public class DouDouToDoListActitvity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	private static EditItemView mRootEditItemView;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private boolean mIsFromNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsFromNotification = getIntent().getBooleanExtra("isNotification", false);
        setContentView(R.layout.main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        
        if(mIsFromNotification){
        	onNavigationDrawerItemSelected(0);
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_todo);
                break;
            case 2:
                mTitle = getString(R.string.title_add_info);
                break;
            case 3:
                mTitle = getString(R.string.title_complete);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
    	if(arg0 == DouDouToDoListUtils.REQUEST_CODE_AFTER_ADD_TODO_ITEM)
    	{
    		onNavigationDrawerItemSelected(0);
    	}
    	if(arg0 == DouDouToDoListUtils.REQUEST_CODE_AFTER_TAKE_PHOTO)
    	{
    		mRootEditItemView.showImageView();
    	}
	}



	/**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	int indexOfView = getArguments().getInt(ARG_SECTION_NUMBER);
        	View rootView;
        	switch (indexOfView) {
			case 1:
				rootView = new TodayToDoView(getActivity(), container);
				return rootView;
			case 2:
				mRootEditItemView = new EditItemView(getActivity(), container);
				return mRootEditItemView;
			case 3:
				rootView = new CompleteToDoView(getActivity(), container);
				return rootView;
			default:
				rootView = new TodayToDoView(getActivity(), container);
				return rootView;
			}
            
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((DouDouToDoListActitvity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
