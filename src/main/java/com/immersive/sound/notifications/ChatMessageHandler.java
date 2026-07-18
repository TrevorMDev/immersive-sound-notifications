package com.immersive.sound.notifications;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatMessageHandler {
    private final SoundEffectPlayer soundEffectPlayer;
    private final ImmersiveSoundNotificationsConfig config;
    private final ConfigParser configParser;

    ChatMessageHandler(SoundEffectPlayer soundEffectPlayer,
                       ImmersiveSoundNotificationsConfig config,
                       ConfigParser configParser) {
        this.soundEffectPlayer = soundEffectPlayer;
        this.config = config;
        this.configParser = configParser;
    }

    public void onChatMessage(String message) {
        // This file uses .contains because some messages have color text inside them.
        try {
            if (message.contains("The effects of the divine potion have worn off.")) {
                for (int[] sound : this.configParser.parseSoundIdsAndVolumes(this.config.divineSounds())) {
                    this.soundEffectPlayer.playSound(sound[0], sound[1]);
                }
            } else if (message.contains("Your cannon is out of ammo!") || message.contains("Your cannon has broken!")) {
                for (int[] sound : this.configParser.parseSoundIdsAndVolumes(this.config.cannonSounds())) {
                    this.soundEffectPlayer.playSound(sound[0], sound[1]);
                }
            } else if(message.contains("Your bracelet of slaughter prevents your slayer count from decreasing. It then crumbles to dust.")) {
                for (int[] sound : this.configParser.parseSoundIdsAndVolumes(this.config.slaughterBraceletSounds())) {
                    this.soundEffectPlayer.playSound(sound[0], sound[1]);
                }
            }
        }
        catch(Exception e) {
            // user likely has a bad formatted config
            // TODO send a game message or let the user know in some way.
            log.debug("error parsing message or config value: {}", e.toString());
        }
    }
}
