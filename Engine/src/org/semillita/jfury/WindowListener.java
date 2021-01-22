package org.semillita.jfury;

public interface WindowListener {

	public void create(Window window);
	
	public void update(Window window);
	
	public void pause(Window window);
	
	public void resume(Window window);
	
	public void resize(int width, int height, Window window);
	
	public void close(Window window);
	
}