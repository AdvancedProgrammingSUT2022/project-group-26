package com.example.project.views.info;

public enum DemographicInfoEnum {
    EXIT("exit"), // done
    SHOW_GOLD("show gold"), // done
    SHOW_MILITARY("show military"), // done
    SHOW_NUM_OF_TILES("show num of tiles"),  // todo :
    SHOW_POPULATION("show population"), // done
    SHOW_HAPPINESS("show happiness"), // done
    SHOW_SCIENCE("show science"), // done
    SHOW_STRATEGIC("show strategic resources"), // todo
    ;

    private String regex;

    DemographicInfoEnum(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}