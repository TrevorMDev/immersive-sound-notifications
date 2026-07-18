package com.immersive.sound.notifications;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.StatChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Immersive Sound Notifications"
)
public class ImmersiveSoundNotificationsPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private transient ClientThread clientThread;

	@Inject
	private ImmersiveSoundNotificationsConfig config;

	private SoundEffectPlayer soundEffectPlayer;
	private ConfigParser configParser;
	private ChatMessageHandler chatMessageHandler;
	private StatChangeHandler statChangeHandler;

	@Override
	protected void startUp() throws Exception
	{
		soundEffectPlayer = new SoundEffectPlayer(client, clientThread);
		configParser = new ConfigParser();
		chatMessageHandler = new ChatMessageHandler(soundEffectPlayer, config, configParser);
		statChangeHandler = new StatChangeHandler(soundEffectPlayer, config, configParser);
		log.debug("Immersive Sound Notifications started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.debug("Immersive Sound Notifications stopped!");
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		if (event.getType() != ChatMessageType.SPAM
				&& event.getType() != ChatMessageType.GAMEMESSAGE)
		{
			return;
		}
		var message = event.getMessage();
		chatMessageHandler.onChatMessage(message);
	}

	@Subscribe
	public void onStatChanged(StatChanged statChanged) {
		this.statChangeHandler.onStatChanged(statChanged);
	}

	@Provides
	ImmersiveSoundNotificationsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ImmersiveSoundNotificationsConfig.class);
	}
}
