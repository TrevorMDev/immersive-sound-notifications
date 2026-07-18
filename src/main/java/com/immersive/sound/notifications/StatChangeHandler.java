package com.immersive.sound.notifications;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Skill;
import net.runelite.api.events.StatChanged;

@Slf4j
public class StatChangeHandler {
    private final SoundEffectPlayer soundEffectPlayer;
    private final ImmersiveSoundNotificationsConfig config;
    private final ConfigParser configParser;
    private double lastRecordedPrayerPercent = -1;

    StatChangeHandler(SoundEffectPlayer soundEffectPlayer,
                      ImmersiveSoundNotificationsConfig config,
                      ConfigParser configParser) {
        this.soundEffectPlayer = soundEffectPlayer;
        this.config = config;
        this.configParser = configParser;
    }

    private void handlePrayerChange(StatChanged statChanged) {
        int currentPrayer = statChanged.getBoostedLevel();
        int maxPrayer = statChanged.getLevel();
        double percent = (double) currentPrayer / maxPrayer;

        // Stat change alert can trigger on login. The -1 default stops it from firing.
        if (this.lastRecordedPrayerPercent == -1) {
            this.lastRecordedPrayerPercent = percent;
            return;
        }
        try {
            if (percent > lastRecordedPrayerPercent) {
                // prayer was restored
                this.lastRecordedPrayerPercent = percent;
                return;
            }
            // Under 50% and every 5% diff.
            else if ((percent < .5 && lastRecordedPrayerPercent - percent > 0.05) ||
                    // Under 10% and every 2% diff.
                    (percent < .1 && lastRecordedPrayerPercent - percent > 0.02) ||
                    // prayer ran out, final catch.
                    currentPrayer == 0 ) {
                for (int[] sound : this.configParser.parseSoundIdsAndVolumes(this.config.prayerSounds())) {
                    // Curve the volume based on current prayer %. Reaches max volume at 0 prayer.
                    int volume = (int) Math.round(sound[1] * (1.0 - Math.pow(percent, 1)));
                    log.debug("percent: {} - volume: {}", percent, volume);
                    this.soundEffectPlayer.playSound(sound[0], volume);
                }
                this.lastRecordedPrayerPercent = percent;
            }
        }
        catch(Exception e) {
            // user likely has a bad formatted config
            // TODO send a game message or let the user know in some way.
            log.debug("error parsing message or config value: {}", e.toString());
        }
    }

    public void onStatChanged(StatChanged statChanged) {
       if(statChanged.getSkill() == Skill.PRAYER) {
           this.handlePrayerChange(statChanged);
       }
    }
}
