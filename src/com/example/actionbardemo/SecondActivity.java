package com.example.actionbardemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SecondActivity extends Activity {

	Spinner sizeOptions;
	Spinner colorOptions;
	Spinner typeOptions;
	
	Settings settings;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		settings = (Settings)getIntent ().getSerializableExtra("foo");
		
		sizeOptions= (Spinner) findViewById(R.id.sizeOptions);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this,
		        R.array.image_sizes, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		sizeOptions.setAdapter(sizeAdapter);
		sizeOptions.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String size = (String)parent.getItemAtPosition(position);
				settings.setImageSize(size);
				//Log.d ("DEBUG", " size = " + size);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
	   colorOptions= (Spinner) findViewById(R.id.colorOptions);
	   colorOptions.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String color = (String)parent.getItemAtPosition(position);
			settings.setColorFilter(color);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	});
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this,
		        R.array.color_filter, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		colorOptions.setAdapter(colorAdapter);
		
		Spinner typeOptions = (Spinner) findViewById(R.id.imageTypeOptions);
		typeOptions.setOnItemSelectedListener(new OnItemSelectedListener ()
		{

			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String imageType = (String)parent.getItemAtPosition(position);
				settings.setImageType(imageType);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
		        R.array.image_type, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		typeOptions.setAdapter(typeAdapter);
	}
	
	public void onSubmit (View v)
	{
		Intent i = new Intent ();
		
		i.putExtra ("value", settings);
		setResult(RESULT_OK, i);
		finish ();
	}
}
