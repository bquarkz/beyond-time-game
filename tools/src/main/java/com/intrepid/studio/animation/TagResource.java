/**
 * Copyleft (C) 2016  Constantino, Nilton Rogerio <niltonrc@gmail.com>
 *
 * @author "Nilton R Constantino"
 * aKa bQUARKz <niltonrc@gmail, bquarkz@gmail.com>
 *
 * Everything about the respective software copyright can be found in the
 * "LICENSE" file included in the project source tree.
 *
 * The code was written based on study principles and can be enjoyed for
 * all comunity without problems.
 */
package com.intrepid.studio.animation;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Json;
import com.intrepid.nicge.utils.animation.AnimationInfo;

class TagResource implements Comparable< TagResource > {
	// ****************************************************************************************
	// Const Fields
	// ****************************************************************************************
	
	// ****************************************************************************************
	// Common Fields
	// ****************************************************************************************
	private FileHandle sai;
	private FileHandle png;
	private Pixmap pixmap;
	private AnimationInfo animationInfo;

	// ****************************************************************************************
	// Constructors
	// ****************************************************************************************
	public TagResource( FileHandle sai, FileHandle png ) {
		this.sai = sai;
		this.png = png;
	}

	// ****************************************************************************************
	// Methods
	// ****************************************************************************************
	public void loadData( Json json ) {
		if( !hasData() ) return;
		
		this.animationInfo = json.fromJson( AnimationInfo.class, this.sai );
		this.pixmap = new Pixmap( this.png );
	}
	
	@Override
	public int compareTo( TagResource that ) {
		// decrescent order
		if( this.getPixmap().getHeight() < that.getPixmap().getHeight() ) return 1;
		if( this.getPixmap().getHeight() > that.getPixmap().getHeight() ) return -1;
		return 0;
	}
	
	// ****************************************************************************************
	// Getters And Setters Methods
	// ****************************************************************************************
	public boolean hasData() {
		return this.sai.exists() && this.png.exists();
	}

	public FileHandle getSai() {
		return sai;
	}
	
	public int getPixmapSize() {
		return pixmap.getWidth() * pixmap.getHeight();
	}
	
	public FileHandle getPng() {
		return png;
	}
	
	public AnimationInfo getAnimationInfo() {
		return animationInfo;
	}
	
	public Pixmap getPixmap() {
		return pixmap;
	}

	// ****************************************************************************************
	// Patterns
	// ****************************************************************************************

}