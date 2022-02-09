package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {
    private static final List<Meal> meals;
    private static volatile AtomicInteger id = new AtomicInteger(7);

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

    private static final MealDao mealDAO = new MealDaoImpl();

    private MealDaoImpl() {
    }

    public static MealDao getInstance() {
        return mealDAO;
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public void save(Meal mealSupplier) {
        Meal meal = null;
        for (Meal mealSearch : meals) {
            if (mealSearch.getId() == mealSupplier.getId()) {
                meal = mealSearch;
            }
        }

        if (meal != null) {
            meal.setDateTime(mealSupplier.getDateTime());
            meal.setDescription(mealSupplier.getDescription());
            meal.setCalories(mealSupplier.getCalories());
        } else {
            meals.add(new Meal(id.incrementAndGet(),
                            mealSupplier.getDateTime(),
                            mealSupplier.getDescription(),
                            mealSupplier.getCalories()));
        }
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
}
