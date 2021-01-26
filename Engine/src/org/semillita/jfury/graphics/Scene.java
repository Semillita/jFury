package org.semillita.jfury.graphics;

import java.util.ArrayList;
import java.util.List;

public final class Scene {
	
	private List<View> views;
		
	public Scene() {
		views = new ArrayList<>();
	}
	
	public void addView(View view) {
		views.add(view);
	}
	
}
