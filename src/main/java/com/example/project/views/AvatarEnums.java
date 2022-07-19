package com.example.project.views;

import com.example.project.App;

import java.net.URL;

public enum AvatarEnums {
    AVATAR_1(App.class.getResource("/Image/Avatars/1.png")),
    AVATAR_2(App.class.getResource("/Image/Avatars/2.png")),
    AVATAR_3(App.class.getResource("/Image/Avatars/3.png")),
    AVATAR_4(App.class.getResource("/Image/Avatars/4.png"));

    private URL url;

    AvatarEnums(URL url) {
        setUrl(url);
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }
}
