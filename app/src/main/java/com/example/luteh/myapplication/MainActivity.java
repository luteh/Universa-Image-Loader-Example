package com.example.luteh.myapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLoad, btnShow;
    private ImageView imageView;

    private ImageLoader imageLoader;
    private UniversalImageItem item;
    private DisplayImageOptions defaultOptions;
    private static final String TAG = "MyActivity";
    private ImageLoaderConfiguration config;

    private final String fileName = "tes.jpg";
    private final String baseDir = "file://" + Environment.getExternalStorageDirectory().getAbsolutePath();
    private final String pathDir = baseDir + "/DCIM/Camera/" + fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = new UniversalImageItem();

        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnShow = (Button) findViewById(R.id.btnShow);

        imageView = (ImageView) findViewById(R.id.imageView);

        btnLoad.setOnClickListener(this);
        btnShow.setOnClickListener(this);

        UILConfiguration();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoad:
                item.setImage(pathDir);
                Log.d(TAG, "onClick: " + item.getImage().toString());
                break;
            case R.id.btnShow:
                imageLoader = imageLoader.getInstance();
                imageLoader.displayImage(item.getImage(), imageView, defaultOptions);
                break;
        }
    }

    public void UILConfiguration() {
        defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .resetViewBeforeLoading(true)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .build();

        ImageLoader.getInstance().init(config);
    }
}
