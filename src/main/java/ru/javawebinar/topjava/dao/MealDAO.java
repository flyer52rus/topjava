package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.datasource.CountId;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDAO {
    List<Meal> getAll();

    void edit(int id, LocalDateTime localDT, String description, int calories);

    Meal get(int id);

    void delete(int id);

    Meal create();

    CountId getId();

    void sort();
}
