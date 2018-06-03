package generisches.lab.trial;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class Phonebook extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapterPhonebook mViewPagerAdapterPhonebook;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        mTabLayout = findViewById(R.id.tablayout_id);
        mViewPager = findViewById(R.id.viewpager_id);
        mViewPagerAdapterPhonebook = new ViewPagerAdapterPhonebook(getSupportFragmentManager());

        //Add fragment here
        mViewPagerAdapterPhonebook.addFragment(new FragmentCall(),"");
        mViewPagerAdapterPhonebook.addFragment(new FragmentContact(),"");
        mViewPagerAdapterPhonebook.addFragment(new FragmentFav(),"");

        mViewPager.setAdapter(mViewPagerAdapterPhonebook);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_call);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_group);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_favorite);

        ActionBar local_actionBar = getSupportActionBar();
        local_actionBar.setElevation(0);

    }
}
