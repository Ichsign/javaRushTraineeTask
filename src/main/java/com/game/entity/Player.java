package com.game.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "player")
public class Player {
    //TODO: finish init and add variables to Specification also
    public static final String ID_FIELD = "id";
    public static final String NAME_FIELD = "name";
    public static final String TITLE_FIELD = "title";
    public static final String RACE_FIELD = "race";
    public static final String PROFESSION_FIELD = "profession";
    public static final String EXP_FIELD = "experience";
    public static final String LVL_FIELD = "level";
    public static final String UNTIL_NEXT_LVL_FIELD = "untilNextLevel";
    public static final String BIRTHDAY_FIELD = "birthday";
    public static final String BANNED_FIELD = "banned";

    @Id
    @Column(name = ID_FIELD)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = NAME_FIELD)
    private String name;

    @Column(name = TITLE_FIELD)
    private String title;

    @Column(name = RACE_FIELD)
    @Enumerated(EnumType.STRING)
    private Race race;

    @Column(name = PROFESSION_FIELD)
    @Enumerated(EnumType.STRING)
    private Profession profession;

    @Column(name = EXP_FIELD)
    private Integer experience;

    @Column(name = LVL_FIELD)
    private Integer level;

    @Column(name = UNTIL_NEXT_LVL_FIELD)
    private Integer untilNextLevel;

    @Column(name = BIRTHDAY_FIELD)
    private Date birthday;

    @Column(name = BANNED_FIELD)
    private Boolean banned = false;


    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

}
