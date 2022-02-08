package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.datasource.CaloriesPerDay;
import ru.javawebinar.topjava.datasource.DataSourceMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalTime;
import java.util.List;

public class ListMealsToAllTime {
    private static List<Meal> meals = DataSourceMemory.getMeals();

    public static List<MealTo> getMealsTo() {
        return MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, CaloriesPerDay.CALORIES_PER_DAY.getCaloriesPerDay());
    }
}
