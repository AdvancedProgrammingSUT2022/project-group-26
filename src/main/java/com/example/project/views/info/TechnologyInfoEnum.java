package com.example.project.views.info;

public enum TechnologyInfoEnum {

    EXIT("exit"), // done
    SHOW_POSSIBLE_TECHNOLOGY("show possible technologies"), // done
    RESEARCH("research (?<technologyName>.+)"), // done
    SHOW_TECH_IN_RESEARCH("show in research technology"), // done
    SHOW_RESEARCHED_TECHS("show researched technologies"), // done
    SHOW_TECHNOLOGY_TREE("show technology tree"), //  todo : ***
    ;
    private String regex;

    TechnologyInfoEnum(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }

}
