package com.example.projectdesigner;

public class Home {
    public String  image;

    public Home(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Home{" +
                "image='" + image + '\'' +
                '}';
    }
}
