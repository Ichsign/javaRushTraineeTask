package com.game.util;

import com.game.controller.request.UpdatePlayerRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class UpdatePlayerRequestValidator<T> implements Validator<UpdatePlayerRequest> {
    @Override
    public boolean isValid(UpdatePlayerRequest target) {
        if (target.getName() != null && !isNameLengthValid(target.getName())) {
            return false;
        }
        if (target.getTitle() != null && !isTitleLengthValid(target.getTitle())) {
            return false;
        }
        if (!ObjectUtils.isEmpty(target.getBirthday())) {
            if (!isBirthdayInRange(target.getBirthday(), 2000, 3000)) return false;
        }
        if (!ObjectUtils.isEmpty(target.getExperience())) {
            if (!isExperienceValid(target.getExperience())) return false;
        }
        return true;
    }
}
