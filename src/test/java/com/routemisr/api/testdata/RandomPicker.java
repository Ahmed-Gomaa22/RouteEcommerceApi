package com.routemisr.api.testdata;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPicker {
    public static <T> T pickRandom(List<T> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Cannot pick a random item if there are no items");
        }
        return items.get(ThreadLocalRandom.current().nextInt(items.size()));
    }
}
