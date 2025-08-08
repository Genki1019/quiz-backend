package com.example.quiz.enumerate;

import lombok.Getter;

@Getter
public enum Category {
    SONG(1, "曲名"),
    PERSON(2, "人物"),
    TERM(3, "音楽用語"),
    OEDO(4, "お江戸コラリアーず");

    private final int key;
    private final String category;

    Category(int key, String category) {
        this.key = key;
        this.category = category;
    }

    public static Category fromKey(int key) {
        for (Category c : Category.values()) {
            if (c.getKey() == key) {
                return c;
            }
        }
        throw new IllegalArgumentException("key: " + key + "は存在しません");
    }

    public static String valueFromKey(int key) {
        return fromKey(key).getCategory();
    }
}
