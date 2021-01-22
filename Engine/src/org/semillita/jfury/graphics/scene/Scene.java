package org.semillita.jfury.graphics.scene;

import java.util.ArrayList;
import java.util.List;

public class Scene {

	private List<View> views;
	
	public Scene() {
		views = new ArrayList<>();
	}
	
	public void addView() {
		
	}
	
	public void draw() {
		for(View view : views);
	}
	
}
