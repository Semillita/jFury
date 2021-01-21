package org.hugo.jfury.sandbox;

import org.hugo.jfury.engine.core.JFury;
import org.hugo.jfury.engine.window.WindowConfig;
import org.hugo.jfury.sandbox.windows.GreenWindow;
import org.hugo.jfury.sandbox.windows.RedWindow;

public class Main {

	public static void main(String[] args) {
		WindowConfig redConfig = new WindowConfig();
		redConfig.r = 1;
		WindowConfig greenConfig = new WindowConfig();
		greenConfig.g = 1;
		JFury.createWindow(new RedWindow(), redConfig);
		JFury.createWindow(new GreenWindow(), greenConfig);
		JFury.start();
	}
	
}