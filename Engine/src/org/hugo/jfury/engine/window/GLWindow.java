package org.hugo.jfury.engine.window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class GLWindow {

	private int width, height;
	private String title;
	private long windowID;
	
	public GLWindow(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		run();
	}
	
	public void run() {
		GLFWErrorCallback.createPrint(System.err).set();
		
		if(!GLFW.glfwInit()) {
			System.err.println("GLFW wasn't initialized");
			return;
		}
		
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_FALSE);
				
		windowID = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		if(windowID == 0) {
			System.err.println("Window wasn't created");
			return;
		}
		
		GLFW.glfwMakeContextCurrent(windowID);
		
		GLFW.glfwSwapInterval(0);
		
		GLFW.glfwShowWindow(windowID);
		GL.createCapabilities();
		
		loop();
	}
	
	public void loop() {
		int fps = 0;
		long lastSecond = System.nanoTime();
		while(!GLFW.glfwWindowShouldClose(windowID)) {
			fps++;
			if(System.nanoTime() - lastSecond >= 1_000_000_000) {
				System.out.println(fps);
				fps = 0;
				lastSecond = System.nanoTime();
			}
			
			GL11.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			GLFW.glfwSwapBuffers(windowID);
			GLFW.glfwPollEvents();
		}
	}
	
}
