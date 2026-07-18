package com.immersive.sound.notifications;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigParser {
    int[][] parseSoundIdsAndVolumes(String config) {
        String[] tokens = config.split(",");

        // (Rows = total sound ids / 2, Columns = 2)
        int[][] pairs = new int[tokens.length / 2][2];

        for (int i = 0; i < tokens.length; i += 2) {
            pairs[i / 2][0] = Integer.parseInt(tokens[i].trim());
            pairs[i / 2][1] = Integer.parseInt(tokens[i + 1].trim());
        }
        return pairs;
    }
}
