package com.immersive.sound.notifications;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ImmersiveSoundNotificationsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ImmersiveSoundNotificationsPlugin.class);
		RuneLite.main(args);
	}
}