package generisches.lab.trial;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class FragmentTabs extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ViewPager mViewPager;

    private SectionsPageAdapter mSectionsPageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tabs);

        Log.d(TAG, "onCreate: Starting");

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(),"Custom Pop Up");
        adapter.addFragment(new Tab2Fragment(), "Async Download");
        adapter.addFragment(new Tab3Fragment(), "Goto Const");
        viewPager.setAdapter(adapter);
    }
}
