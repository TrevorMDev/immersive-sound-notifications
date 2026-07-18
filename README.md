# Immersive Sound Notifications

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://buymeacoffee.com/trevormdev)

---

This plugin adds sound queues for specific events.

Supported events:
- Divine expired
- Cannon out of ammo or broken
- Slaughter bracelet breaking
- Low prayer
  - Scales volume based on current prayer %, maxing at 0 prayer.
  - Under 50% prayer, plays every 5% change.
  - Under 10% prayer, plays every 2% change.

The sound effects can be changed(using in game soundIds) but the triggered events cannot.
This is to limit the power of the plugin and avoid the need to disable it in tons of locations like WatchDog.
Requests and PR's welcome.

---

The sounds in the config are the soundId followed by the volume of that sound.

`5243,20,2225,10` translates to soundId 5243 at volume 20 AND soundId 2225 at volume 10.
All sounds included will be played together on the trigger.

SoundIds can be found on [this wiki page](https://oldschool.runescape.wiki/w/List_of_sound_IDs).