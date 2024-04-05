package com.example.bt_29_3_2024.model;

public class NewMeal {
    private String id;
    private String meal;
    private String area;
    private String category;
    private String instructions;
    private String strmealthumb;
    private String price;

    public NewMeal(
            String id, String meal, String area, String category, String instructions,
            String strmealthumb, String price
    ) {
        this.id = id;
        this.meal = meal;
        this.area = area;
        this.category = category;
        this.instructions = instructions;
        this.strmealthumb = strmealthumb;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getStrmealthumb() {
        return strmealthumb;
    }

    public void setStrmealthumb(String strmealthumb) {
        this.strmealthumb = strmealthumb;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
