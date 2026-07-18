package com.immersive.sound.notifications;

import net.runelite.api.Client;
import net.runelite.client.callback.ClientThread;


public class SoundEffectPlayer {
    private final Client client;
    private final ClientThread clientThread;

    public SoundEffectPlayer(Client client, ClientThread clientThread) {
        this.client = client;
        this.clientThread = clientThread;
    }

    public void playSound(int soundId, int volume) {
        this.clientThread.invokeLater(() -> {
            var userVolume = this.client.getPreferences().getSoundEffectVolume();
            this.client.getPreferences().setSoundEffectVolume(volume);
            this.client.playSoundEffect(soundId, 0);
            this.client.getPreferences().setSoundEffectVolume(userVolume);
        });
    }

}
