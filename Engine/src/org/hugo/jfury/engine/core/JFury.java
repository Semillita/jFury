package org.hugo.jfury.engine.core;

import java.util.ArrayList;
import java.util.List;

import org.hugo.jfury.engine.window.Window;
import org.hugo.jfury.engine.window.WindowConfig;
import org.hugo.jfury.engine.window.WindowListener;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class JFury {

	private static boolean initialized;
	private static boolean running;
	private static List<GLWindow> glWindows;
	private static List<GLWindow> closedWindows;

	public static void createWindow(WindowListener listener, WindowConfig config) {
		if (!initialized)
			init();
		glWindows.add(new GLWindow(listener, config));
	}

	private static void init() {
		if (!GLFW.glfwInit())
			System.err.println("[Failed to initialize GLFW]");
		glWindows = new ArrayList<>();
		closedWindows = new ArrayList<>();
		initialized = true;
	}

	public static void start() {
		if (running) {
			System.err.println("[Failed to start engine -> Already running]");
			return;
		} else if (glWindows == null || glWindows.isEmpty()) {
			System.err.println("[Failed to start engine -> No windows found]");
			return;
		}
		GLFWErrorCallback.createPrint(System.err).set();
		loop();
	}

	private static void loop() {
		running = true;
		while (!glWindows.isEmpty()) {
			for (GLWindow glWindow : glWindows)
				glWindow.update();
			glWindows.removeAll(closedWindows);
			closedWindows.clear();
		}
	}

	private static class GLWindow {
		private final WindowListener listener;
		private final WindowConfig config;
		long handle;

		public GLWindow(WindowListener listener, WindowConfig config) {
			this.listener = listener;
			this.config = config;
			create();
		}

		void create() {
			System.out.println("create");
			GLFW.glfwDefaultWindowHints();
			GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, 0);
			if (config.resizable)
				GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, 1);
			long monitor = 0;
			if (config.fullscreen)
				monitor = GLFW.glfwGetPrimaryMonitor();
			handle = GLFW.glfwCreateWindow(config.width, config.height, config.title, monitor, 0);
			if (handle == 0) {
				System.err.println("[Failed to create window " + config.title + "]");
				return;
			}
			GLFW.glfwMakeContextCurrent(handle);
			GLFW.glfwSwapInterval(1);
			GLFW.glfwShowWindow(handle);
			GL.createCapabilities();
		}

		void update() {
			GLFW.glfwMakeContextCurrent(handle);
			if (GLFW.glfwWindowShouldClose(handle)) {
				close();
			}
			GL11.glClearColor(config.r, config.g, config.b, 1);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			//Draw sprites here
			GLFW.glfwSwapBuffers(handle);
			GLFW.glfwPollEvents();
		}

		void close() {
			closedWindows.add(this);
			GLFW.glfwDestroyWindow(this.handle);
		}
		
	}

}
