package generisches.lab.trial;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Sangita on 13-05-2018.
 */

public class SliderAdapter extends PagerAdapter {

    Context mcontext;
    LayoutInflater mLayoutInflater;

    public SliderAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    //Arrays
    public int[] slide_images={
            R.drawable.eat_icon,
            R.drawable.sleep_icon,
            R.drawable.code_icon
    };
    public String[] slide_headings={
            "EAT",
            "SLEEP",
            "CODE"
    };
    public String[] slide_desc={
            "Ich weis nicht uber du. Aber mich... Ich kenne :) Ich mag essen.",
            "Ich weis nicht uber du. Aber mich... Ich kenne :) Ich mag schlafen.",
            "Ich weis nicht uber du. Aber mich... Ich kenne :) Ich liebe programmieren"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      mLayoutInflater = (LayoutInflater) mcontext.getSystemService(mcontext.LAYOUT_INFLATER_SERVICE);
      View view = mLayoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = view.findViewById(R.id.slide_heading);
        TextView slideText = view.findViewById(R.id.slide_text);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideText.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
