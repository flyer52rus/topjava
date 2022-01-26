package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );


        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

 //       System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();

        Collections.sort(meals, (o1, o2) -> {
            if (o1.getDateTime().isAfter(o2.getDateTime())) {
                return 1;
            } else if (o1.getDateTime().isBefore(o2.getDateTime())) {
                return -1;
            } else {
                return 0;
            }
        });

        HashMap<LocalDate, Integer> mapDateAndCalories = new HashMap<>();

        LocalDate localDate = meals.get(0).getDateTime().toLocalDate();
        int calories = 0;
        for (UserMeal um: meals) {
            LocalDate localDateForEach = um.getDateTime().toLocalDate();
            if (localDate.equals(localDateForEach)) {
                calories += um.getCalories();
            } else {
                mapDateAndCalories.put(localDate, calories);
                calories = um.getCalories();
                localDate = localDateForEach;
            }
        }
        mapDateAndCalories.put(localDate, calories);

        for (UserMeal um: meals) {
            LocalTime localTimeMeals = um.getDateTime().toLocalTime();

            if (startTime.isBefore(localTimeMeals) && endTime.isAfter(localTimeMeals)) {

                int summCaloriesInDay = mapDateAndCalories.get(um.getDateTime().toLocalDate());

                boolean checkExcess = summCaloriesInDay > caloriesPerDay;
                userMealWithExcesses.add(new UserMealWithExcess(um.getDateTime(), um.getDescription(), um.getCalories(), checkExcess));
            }
        }
        return userMealWithExcesses;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();

        Stream<UserMeal> streamFromUserMeal = meals.stream();

        List<UserMeal> list =
        streamFromUserMeal.filter((um) -> um.getDateTime().toLocalTime().isAfter(startTime) &&
                                            um.getDateTime().toLocalTime().isBefore(endTime))
                                            .collect(Collectors.toList());


       // System.out.println(streamFromUserMeal.collect(Collectors.toList()).toString());

        return userMealWithExcesses;
    }
}
