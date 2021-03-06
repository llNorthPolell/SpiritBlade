package com.northpole.spiritblade.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.northpole.spiritblade.SpiritBlade;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.vSyncEnabled = true;
		config.useGL30 = true;
		new LwjglApplication(new SpiritBlade(), config);
	}
}
