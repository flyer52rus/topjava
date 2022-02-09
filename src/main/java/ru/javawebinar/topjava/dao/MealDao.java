package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    List<Meal> getAll();

    void save(Meal mealSupplier);

    Meal get(int id);

    void delete(int id);
}
