package com.game.util;

import com.game.controller.request.CreatePlayerRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;



@Component
public class CreatePlayerRequestValidator implements Validator<CreatePlayerRequest> {
    @Override
    public boolean isValid(CreatePlayerRequest target) {
        if (StringUtils.isEmpty(target.getName()) || !isNameLengthValid(target.getName())) {
            return false;
        }
        if (StringUtils.isEmpty(target.getTitle()) || !isTitleLengthValid(target.getTitle())) {
            return false;
        }
        if (ObjectUtils.isEmpty(target.getRace())) {
            return false;
        }
        if (ObjectUtils.isEmpty(target.getProfession())) {
            return false;
        }
        if (ObjectUtils.isEmpty(target.getBirthday()) || target.getBirthday() < 0) {
            return false;
        } else {
            if (!isBirthdayInRange(target.getBirthday(), 2000, 3000)) return false;
        }
        if (ObjectUtils.isEmpty(target.getExperience()) || !isExperienceValid(target.getExperience())) {
            return false;
        }
        return true;
    }
}
