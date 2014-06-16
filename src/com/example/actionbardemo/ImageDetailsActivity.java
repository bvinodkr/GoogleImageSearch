package com.example.actionbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

public class ImageDetailsActivity extends Activity {

	SmartImageView iView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_details);
		String imageUrl = getIntent().getStringExtra("imageUrl");
		//Toast.makeText(getApplicationContext(), " url = " + imageUrl, Toast.LENGTH_SHORT).show();
		iView = (SmartImageView)findViewById(R.id.ivResult);
		iView.setImageUrl(imageUrl);
	}
}
