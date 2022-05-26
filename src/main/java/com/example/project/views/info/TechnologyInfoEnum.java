package com.example.project.views.info;

public enum TechnologyInfoEnum {

    EXIT("exit"),
    SHOW_POSSIBLE_TECHNOLOGY("show possible technologies"),
    RESEARCH("research (?<technologyName>.+)"),
    SHOW_TECH_IN_RESEARCH("show in research technology"),
    SHOW_RESEARCHED_TECHS("show researched technologies"),
    SHOW_TECHNOLOGY_TREE("show technology tree"),
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
