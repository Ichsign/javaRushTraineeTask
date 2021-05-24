package com.game.util;

import java.util.Calendar;

import static com.game.util.Constants.NAME_MAX_LENGTH;
import static com.game.util.Constants.TITLE_MAX_LENGTH;

public interface Validator<T> {
    boolean isValid(T target);

    default boolean isNameLengthValid(String name) {
        return name != null && name.length() <= NAME_MAX_LENGTH && name.length() > 0;
    }

    default boolean isTitleLengthValid(String title) {
        return title != null && title.length() <= TITLE_MAX_LENGTH && title.length() > 0;
    }

    default boolean isExperienceValid(Integer exp) {
        return exp != null && exp >= 0 && exp <= 10_000_000;
    }

    default boolean isBirthdayInRange(Long birthday, int start, int end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(birthday);
        int checkYear = calendar.get(Calendar.YEAR);
        if (checkYear < start || checkYear > end) {
            return false;
        }
        return true;
    }
}
