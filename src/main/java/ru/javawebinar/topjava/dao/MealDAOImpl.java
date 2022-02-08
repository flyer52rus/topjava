package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.datasource.CountId;
import ru.javawebinar.topjava.datasource.DataSourceMemory;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MealDAOImpl implements MealDAO {
    private List<Meal> meals = getAll();
    private static final MealDAO mealDAO = new MealDAOImpl();

    private MealDAOImpl() {
    }

    public static MealDAO getInstance() {
        return mealDAO;
    }

    @Override
    public List<Meal> getAll() {
        return DataSourceMemory.getMeals();
    }

    @Override
    public void edit(int id, LocalDateTime localDT, String description, int calories) {
        Meal meal = get(id);
        meal.setDateTime(localDT);
        meal.setDescription(description);
        meal.setCalories(calories);
    }

    @Override
    public Meal get(int id) {
        for (Meal meal : meals) {
            if (meal.getId() == id) {
                return meal;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId() == id) {
                meals.remove(i);
                break;
            }
        }
    }

    @Override
    public Meal create() {
        Meal meal = new Meal(mealDAO.getId(),
                            LocalDateTime.of(LocalDate.now().getYear(),
                            LocalDate.now().getMonth(),
                            LocalDate.now().getDayOfMonth(),
                            LocalTime.now().getHour(),
                            LocalTime.now().getMinute()),"",0);
        meals.add(meal);
        return meal;
    }

    @Override
    public CountId getId() {
        return CountId.getInstance();
    }

    @Override
    public void sort() {
        Collections.sort(meals, Comparator.comparing(Meal::getDateTime));
    }
}
