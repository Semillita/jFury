package org.semillita.jfury.sandbox.windows;

import org.semillita.jfury.Window;
import org.semillita.jfury.WindowListener;

public class RedWindow implements WindowListener {

	@Override
	public void create(Window window) {
		System.out.println("Red window was created");
	}

	@Override
	public void update(Window window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause(Window window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume(Window window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height, Window window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close(Window window) {
		System.out.println("Red window was closed");
	}

}
