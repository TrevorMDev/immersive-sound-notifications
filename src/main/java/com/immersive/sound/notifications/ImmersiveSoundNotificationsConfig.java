package com.immersive.sound.notifications;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface ImmersiveSoundNotificationsConfig extends Config
{
	@ConfigItem(
		keyName = "divineSounds",
		name = "Divine Expired",
		description = "The sounds/volumes to play when a divine potion expires"
	)
	default String divineSounds()
	{
		return "5243,20,2225,10";
	}

	@ConfigItem(
			keyName = "cannonSounds",
			name = "Cannon Ammo",
			description = "The sounds/volumes to play when your cannon runs out of ammo or breaks"
	)
	default String cannonSounds()
	{
		return "3837,10";
	}

	@ConfigItem(
			keyName = "slaughterBraceletSounds",
			name = "Slaughter Bracelet",
			description = "The sounds/volumes to play when your slaughter bracelet breaks"
	)
	default String slaughterBraceletSounds()
	{
		return "2974,15";
	}

	@ConfigItem(
			keyName = "prayerSounds",
			name = "Prayer Sounds",
			description = "The sounds/volumes to play as your prayer gets low"
	)
	default String prayerSounds() { return "9108,30"; }

}
