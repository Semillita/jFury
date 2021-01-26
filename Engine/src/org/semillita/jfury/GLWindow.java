package org.semillita.jfury;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.semillita.jfury.graphics.Scene;
import org.semillita.jfury.graphics.shaders.Shader;

final class GLWindow {
	
	long handle;
	private final WindowListener listener;
	private final WindowConfig config;
	int width, height;
	String title;
	int x, y;
	
	boolean fullscreen;
	boolean resizable;
	
	private Scene scene;
	
	Shader shader;

	GLWindow(WindowListener listener, WindowConfig config) {
		this.listener = listener;
		this.config = config;
		create();
	}

	void create() {
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
		listener.create(new Window(this));
		shader = new Shader();
		shader.init();
	}

	void update() {
		GLFW.glfwMakeContextCurrent(handle);
		if (GLFW.glfwWindowShouldClose(handle)) {
			close();
		}
		listener.update(new Window(this));
		GL30.glClearColor(config.r, config.g, config.b, 1);
		GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		// Draw sprites here
		shader.update();
		GLFW.glfwSwapBuffers(handle);
		GLFW.glfwPollEvents();
	}

	void close() {
		listener.close(new Window(this));
		JFury.closeWindow(this);
	}

	void setScene(Scene scene) {
		this.scene = scene;
	}

}
