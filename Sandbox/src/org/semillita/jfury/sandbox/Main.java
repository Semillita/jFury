package org.semillita.jfury.sandbox;

import org.semillita.jfury.JFury;
import org.semillita.jfury.WindowConfig;
import org.semillita.jfury.sandbox.windows.GreenWindow;
import org.semillita.jfury.sandbox.windows.RedWindow;

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