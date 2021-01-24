package org.semillita.jfury;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.semillita.jfury.graphics.Scene;

public class JFury {

	private static boolean initialized;
	private static boolean running;
	private static List<GLWindow> windows;
	private static List<GLWindow> newWindows;
	private static List<GLWindow> closedWindows;

	public static void createWindow(WindowListener listener, WindowConfig config) {
		if (!initialized) init();
		newWindows.add(new GLWindow(listener, config));
	}
	
	static void closeWindow(GLWindow glWindow) {
		closedWindows.add(glWindow);
	}
	
	private static void init() {
		if (!GLFW.glfwInit()) System.err.println("[Failed to initialize GLFW]");
		windows = new ArrayList<>();
		newWindows = new ArrayList<>();
		closedWindows = new ArrayList<>();
		initialized = true;
	}
	
	public static void start() {
		if (running) {
			System.err.println("[Failed to start engine -> Already running]");
			return;
		}
		updateLayout();
		if (windows == null || windows.isEmpty()) {
			System.err.println("[Failed to start engine -> No windows found]");
			return;
		}
		GLFWErrorCallback.createPrint(System.err).set();
		loop();
	}

	private static void loop() {
		running = true;
		while (!windows.isEmpty()) {
			for (GLWindow window : windows) window.update();
			updateLayout();
		}
	}
	
	private static void updateLayout() {
		windows.addAll(newWindows);
		newWindows.clear();
		for(GLWindow window : closedWindows) {
			windows.remove(window);
			GLFW.glfwDestroyWindow(window.handle);
		}
		closedWindows.clear();
	}
	
}
