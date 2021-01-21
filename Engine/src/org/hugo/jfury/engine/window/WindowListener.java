package org.hugo.jfury.engine.window;

public interface WindowListener {

	public void create(Window window);
	
	public void update(Window window);
	
	public void pause(Window window);
	
	public void resume(Window window);
	
	public void resize(Window window);
	
	public void destroy(Window window);
	
}