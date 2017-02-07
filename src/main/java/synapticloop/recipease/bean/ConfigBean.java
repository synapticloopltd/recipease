package synapticloop.recipease.bean;

/*
 * Copyright (c) 2017 Synapticloop.
 * 
 * All rights reserved.
 * 
 * This code may contain contributions from other parties which, where 
 * applicable, will be listed in the default build file for the project 
 * ~and/or~ in a file named CONTRIBUTORS.txt in the root of the project.
 * 
 * This source code and any derived binaries are covered by the terms and 
 * conditions of the Licence agreement ("the Licence").  You may not use this 
 * source code or any derived binaries except in compliance with the Licence.  
 * A copy of the Licence is available in the file named LICENSE.txt shipped with 
 * this source code or binaries.
 */

public class ConfigBean {
	private final String pageMarginRight;
	private final String pageMarginLeft;
	private final String pageMarginBottom;
	private final String pageMarginTop;
	private final String pageWidth;
	private final String pageHeight;
	private final String regionBodyMarginTop;
	private final String regionBodyMarginBottom;
	private final String regionBeforeExtent;
	private final String regionAfterExtent;
	private final String imageWidth;

	public ConfigBean(String pageMarginRight, String pageMarginLeft, String pageMarginBottom, 
			String pageMarginTop, String pageWidth, String pageHeight, String regionBodyMarginTop, 
			String regionBodyMarginBottom, String regionBeforeExtent, String regionAfterExtent,
			String imageWidth) {

		this.pageMarginRight = pageMarginRight;
		this.pageMarginLeft = pageMarginLeft;
		this.pageMarginBottom = pageMarginBottom;
		this.pageMarginTop = pageMarginTop;
		this.pageWidth = pageWidth;
		this.pageHeight = pageHeight;
		this.regionBodyMarginTop = regionBodyMarginTop;
		this.regionBodyMarginBottom = regionBodyMarginBottom;
		this.regionBeforeExtent = regionBeforeExtent;
		this.regionAfterExtent = regionAfterExtent;
		this.imageWidth = imageWidth;
	}

	public String getPageMarginRight() { return this.pageMarginRight; }
	public String getPageMarginLeft() { return this.pageMarginLeft; }
	public String getPageMarginBottom() { return this.pageMarginBottom; }
	public String getPageMarginTop() { return this.pageMarginTop; }
	public String getPageWidth() { return this.pageWidth; }
	public String getPageHeight() { return this.pageHeight; }
	public String getRegionBodyMarginTop() { return this.regionBodyMarginTop; }
	public String getRegionBodyMarginBottom() { return this.regionBodyMarginBottom;  }
	public String getRegionBeforeExtent() { return this.regionBeforeExtent; }
	public String getRegionAfterExtent() { return this.regionAfterExtent; }
	public String getImageWidth() { return this.imageWidth; }

}
