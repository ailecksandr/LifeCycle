package com.example.sunstrikovich.lifecycle;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SoundPool soundPlayer;

    private void addPage(List<View> pages, final int picture){
        LayoutInflater inflater = LayoutInflater.from(this);
        View page = inflater.inflate(R.layout.page, null);
        final ImageView imageView = (ImageView) page.findViewById(R.id.imageView);
        Picasso.with(this)
                .load(picture)
                .error(R.drawable.locked)
                .into(imageView);
        pages.add(page);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundPlayer = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundPlayer.load(this, R.raw.page_flip, 1);

        final TextView currentYear=(TextView) findViewById(R.id.textView);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        SeekBar seekBar=(SeekBar) findViewById(R.id.seekBar);
        final ProgressBar progress=(SeekBar) findViewById(R.id.seekBar);
        List<View> pages = new ArrayList<View>();

        seekBar.getThumb().mutate().setAlpha(0);

        addPage(pages, R.drawable.one);
        addPage(pages, R.drawable.two);
        addPage(pages, R.drawable.three);
        addPage(pages, R.drawable.four);
        addPage(pages, R.drawable.five);
        addPage(pages, R.drawable.six);
        addPage(pages, R.drawable.seven);
        addPage(pages, R.drawable.eight);
        addPage(pages, R.drawable.nine);
        addPage(pages, R.drawable.ten);
        addPage(pages, R.drawable.oone);
        addPage(pages, R.drawable.otwo);
        addPage(pages, R.drawable.othree);
        addPage(pages, R.drawable.ofour);
        addPage(pages, R.drawable.ofive);
        addPage(pages, R.drawable.osix);
        addPage(pages, R.drawable.oseven);
        addPage(pages, R.drawable.oeight);
        addPage(pages, R.drawable.onine);
        addPage(pages, R.drawable.twenty);
        addPage(pages, R.drawable.tone);
        addPage(pages, R.drawable.ttwo);
        addPage(pages, R.drawable.tthree);
        addPage(pages, R.drawable.tfour);
        addPage(pages, R.drawable.tfive);

        for (int i=0; i<75; i++){
            addPage(pages, R.drawable.locked);
        }
        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(pages);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(24);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewPager.setCurrentItem(seekBar.getProgress());
                currentYear.setText(String.valueOf(seekBar.getProgress()+1));
                if (seekBar.getProgress() == 24) {
                    SpannableString content = new SpannableString("25");
                    content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                    currentYear.setText(content);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.getThumb().mutate().setAlpha(255);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBar.getThumb().mutate().setAlpha(0);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                soundPlayer.play(1, 1.f, 1.f, 2, 0, 1);
            }

            @Override
            public void onPageSelected(int position) {
                currentYear.setText(String.valueOf(position + 1));
                progress.setProgress(position);
                if (position == 24) {
                    SpannableString content = new SpannableString("25");
                    content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                    currentYear.setText(content);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    protected void onResume(){
        super.onResume();
        mediaPlayer=MediaPlayer.create(this.getApplicationContext(), R.raw.backsound);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
