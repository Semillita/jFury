package org.semillita.jfury.graphics.scene;

public abstract class View {

	private int offsetX = 0, offsetY = 0;
	private int width = -1, height = -1;
	
	public View(int offsetX, int offsetY, int width, int height) {
		this.offsetX = 0;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
	}
	
	
	
}
