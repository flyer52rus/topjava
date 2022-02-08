package ru.javawebinar.topjava.datasource;

public enum CaloriesPerDay {
    CALORIES_PER_DAY(2000);

    private final int caloriesPerDay;

    CaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }
}
