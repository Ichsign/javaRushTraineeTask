package com.game.service;

import com.game.controller.request.FilterRequest;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class Specifications {

    public static Specification<Player> playerSpecification(FilterRequest request) {
        Specification<Player> specification = Specification.where(null);
        if (request.getName() != null) {
            specification = specification.and(nameSpecification(request.getName()));
        }
        if (request.getTitle() != null) {
            specification = specification.and(titleSpecification(request.getTitle()));
        }
        if (request.getRace() != null) {
            specification = specification.and(raceSpecification(request.getRace()));
        }
        if (request.getProfession() != null) {
            specification = specification.and(professionSpecification(request.getProfession()));
        }
        if (request.getAfter() != null) {
            specification = specification.and(birthdayAfterSpecification(request.getAfter()));
        }
        if (request.getBefore() != null) {
            specification = specification.and(birthdayBeforeSpecification(request.getBefore()));
        }
        if (request.getBanned() != null) {
            specification = specification.and(bannedSpecification(request.getBanned()));
        }
        if (request.getMinExperience() != null) {
            specification = specification.and(minExperienceSpecification(request.getMinExperience()));
        }
        if (request.getMaxExperience() != null) {
            specification = specification.and(maxExperienceSpecification(request.getMaxExperience()));
        }
        if (request.getMinLevel() != null) {
            specification = specification.and(minLevelSpecification(request.getMinLevel()));
        }
        if (request.getMaxLevel() != null) {
            specification = specification.and(maxLevelSpecification(request.getMaxLevel()));
        }
        return specification;
    }

    private static Specification<Player> maxLevelSpecification(Integer lvl) {
        return (root, query, criterialBuilder) -> criterialBuilder.lessThanOrEqualTo(root.get("level"), lvl);
    }

    private static Specification<Player> minLevelSpecification(Integer lvl) {
        return (root, query, criterialBuilder) -> criterialBuilder.greaterThanOrEqualTo(root.get("level"), lvl);
    }

    private static Specification<Player> maxExperienceSpecification(Integer exp) {
        return (root, query, criterialBuilder) -> criterialBuilder.lessThanOrEqualTo(root.get("experience"), exp);
    }

    private static Specification<Player> minExperienceSpecification(Integer exp) {
        return (root, query, criterialBuilder) -> criterialBuilder.greaterThanOrEqualTo(root.get("experience"), exp);
    }

    private static Specification<Player> bannedSpecification(Boolean ban) {
        return (root, query, criterialBuilder) -> criterialBuilder.equal(root.get("banned"), ban);
    }

    private static Specification<Player> birthdayBeforeSpecification(Long birthday) {
        return (root, query, criterialBuilder) -> criterialBuilder.lessThan(root.get("birthday"), new Date(birthday));
    }

    private static Specification<Player> birthdayAfterSpecification(Long birthday) {
        return (root, query, criterialBuilder) -> criterialBuilder.greaterThan(root.get("birthday"), new Date(birthday));
    }

    private static Specification<Player> professionSpecification(Profession profession) {
        return (root, query, criterialBuilder) -> criterialBuilder.equal(root.get("profession"), profession);
//        return (root, query, criterialBuilder) -> criterialBuilder.equal(root.get("profession"), profession.name());
    }

    private static Specification<Player> nameSpecification(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    private static Specification<Player> titleSpecification(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + name + "%");
    }

    private static Specification<Player> raceSpecification(Race race) {
        return (root, query, criterialBuilder) -> criterialBuilder.equal(root.get("race"), race);
//        return (root, query, criterialBuilder) -> criterialBuilder.equal(root.get("race"), race.name());
    }

}
