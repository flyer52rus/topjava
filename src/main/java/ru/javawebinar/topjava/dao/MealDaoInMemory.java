package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoInMemory implements MealDao {
    private static final List<Meal> meals;
    private static final AtomicInteger counter = new AtomicInteger(7);

    static {
        meals = new CopyOnWriteArrayList<>();
        meals.add(new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.add(new Meal(5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    private static final MealDao mealDAO = new MealDaoInMemory();

    private MealDaoInMemory() {
    }

    public static MealDao getInstance() {
        return mealDAO;
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals) ;
    }

    @Override
    public synchronized Meal save(Meal meal) {
        if (meal.getId() == null) {
            Integer id = counter.incrementAndGet();
            meals.add(new Meal( id,
                    meal.getDateTime(),
                    meal.getDescription(),
                    meal.getCalories()));
            return get(id);
        } else {
            for (Meal mealSearch : meals) {
                if (mealSearch.getId().equals(meal.getId())) {
                    mealSearch.setDateTime(meal.getDateTime());
                    mealSearch.setDescription(meal.getDescription());
                    mealSearch.setCalories(meal.getCalories());
                    return get(mealSearch.getId());
                }
            }
        }
        return null;
    }

    @Override
    public Meal get(Integer id) {
        if (id == null) {
            return new Meal(null, LocalDateTime.of(LocalDate.now().getYear(),
                    LocalDate.now().getMonth(),
                    LocalDate.now().getDayOfMonth(),
                    LocalTime.now().getHour(),
                    LocalTime.now().getMinute()), "", 0);
        } else {
            for (Meal meal : meals) {
                if (meal.getId().equals(id)) {
                    return new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId().equals(id)) {
                meals.remove(i);
                break;
            }
        }
    }
}
