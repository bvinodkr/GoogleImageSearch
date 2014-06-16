package com.example.actionbardemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultArrayAdapter(Context context,
			List<ImageResult> objects) {
		super(context, R.layout.image_result_item, objects);
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult imageInfo = this.getItem(position);
		SmartImageView ivImage;
		if (convertView == null)
		{
			LayoutInflater inflator = LayoutInflater.from(getContext ());
			ivImage = (SmartImageView)inflator.inflate(R.layout.image_result_item, parent, false);
		}
		else
		{
			ivImage = (SmartImageView)convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		ivImage.setImageUrl(imageInfo.getThumbUrl());
		return ivImage;
	}
	

}
