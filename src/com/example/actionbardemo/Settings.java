package com.example.actionbardemo;

import java.io.Serializable;

public class Settings implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String imageSize;
 private String imageType;
 private String colorFilter;
 private String siteFilter;
 
 public Settings ()
 {
	 imageSize = "";
	 imageType = "";
	 colorFilter = "";
	 siteFilter = "";
 }

public String getImageSize() {
	return imageSize;
}

public void setImageSize(String imageSize) {
	this.imageSize = imageSize;
}

public String getImageType() {
	return imageType;
}

public void setImageType(String imageType) {
	this.imageType = imageType;
}

public String getColorFilter() {
	return colorFilter;
}

public void setColorFilter(String colorFilter) {
	this.colorFilter = colorFilter;
}

public String getSiteFilter() {
	return siteFilter;
}

public void setSiteFilter(String siteFilter) {
	this.siteFilter = siteFilter;
}
 
}
