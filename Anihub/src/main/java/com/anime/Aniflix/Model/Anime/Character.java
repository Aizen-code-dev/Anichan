package com.anime.Aniflix.Model.Anime;


import java.util.List;

public class Character {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public CharacterName getName() {
        return name;
    }

    public void setName(CharacterName name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String role;
    private CharacterName name;
    private String image;


}

