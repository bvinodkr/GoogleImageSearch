package com.example.actionbardemo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MainActivity extends Activity {

	EditText etQuery;
	Button srchButton;
	GridView gvResults;
	Settings settings;
	
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.settings = new Settings ();
		setupViews ();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent (getApplicationContext(), ImageDetailsActivity.class);
				ImageResult image = imageResults.get(position);
				 //pass data
				 i.putExtra("imageUrl", image.getFullUrl());
				 startActivityForResult(i, 50);
			}
			
		});
		
		gvResults.setOnScrollListener(new EndlessScrollListener() {
		    @Override
		    public void onLoadMore(int page, int totalItemsCount) {
	                // Triggered only when new data needs to be appended to the list
	                // Add whatever code is needed to append new items to your AdapterView
		        customLoadMoreDataFromApi(page, totalItemsCount); 
	                // or customLoadMoreDataFromApi(totalItemsCount); 
		    }
	        });
	}
	
	  // Append more data into the adapter
    public void customLoadMoreDataFromApi(int offset, int totalItemsCount) {
      // This method probably sends out a network request and appends new data items to your adapter. 
      // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
      // Deserialize API response and then construct new objects to append to the adapter
    	searchQuery (offset, false);
    }
	
	public void setupViews ()
	{
		etQuery = (EditText)findViewById(R.id.imgSrch);
		srchButton = (Button)findViewById (R.id.Search);
		gvResults = (GridView)findViewById (R.id.gridView1);
	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.menu_simple, menu);
	        return true;
	    }
	 private String getURL (int page)
	 {
		 String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&rsz=8&start=" + page;

		 if (settings.getColorFilter() != "")
		 {
			 url = url + "&imgcolor=" + settings.getColorFilter();
			 
		 }

		 if (settings.getImageSize() != "")
		 {
			 url = url + "&imgsz=" + settings.getImageSize();
		 }

		 if (settings.getImageType() != "")
		 {
			 url = url + "&imgtype=" + settings.getImageType();
		 }

		 if (settings.getSiteFilter() != "")
		 {
			 url = url + "&as_sitesearch=" + settings.getSiteFilter();
		 }
		 
		 return url;
	 }
	 
	 public void searchQuery (int page, final boolean clear)
	 {
		 String query = etQuery.getText().toString();
		 if (query == "")
		 {
			 return;
		 }
		// Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT).show();
		 AsyncHttpClient client = new AsyncHttpClient ();
		 //"https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=barack%20obama&userip=INSERT-USER-IP");
		 String url = getURL(page) ;
		 Log.d ("DEBUG", "url = " + url);
		 client.get(url +  "&q=" + Uri.encode(query),
				 new JsonHttpResponseHandler() {
			 @Override
			 public void onSuccess (JSONObject response)
			 {
				 JSONArray imageJsonResults = null;
				 try
				 {
					 //Log.d ("DEBUG", "response data = " + response.toString(2));
					 imageJsonResults = response.getJSONObject(
							 "responseData").getJSONArray("results");
					 if (clear == true)
					 {
						 imageResults.clear ();	 
					 }
					 
					 imageAdapter.addAll(ImageResult.fromJSONArray (imageJsonResults));
					 //Log.d ("DEBUG", imageResults.toString ());
				 }
				 catch (JSONException e)
				 {
					Log.d ("DEBUG", "error on http request" + e.getMessage()); 
				 }
			 }
		 });
		 
	 }
	 
	 public void onImageSearch (View view)
	 {
		 searchQuery (0, true);
	 }
	 
	
	 
	 public void onSettings (MenuItem mi)
	 {
		 Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
		 Intent i = new Intent (this, SecondActivity.class);
		 //pass data
		 i.putExtra("foo", settings);
		 startActivityForResult(i, 50);
		 
		 
	 }
	 
	 @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if (resultCode == RESULT_OK)
		 {
			 if (requestCode == 50)
			 {
				 settings = (Settings)data.getSerializableExtra("value");
				 Toast.makeText(this, settings.getImageSize(), Toast.LENGTH_SHORT);
				 searchQuery(0, true);
			 }
		 }
	}
}
