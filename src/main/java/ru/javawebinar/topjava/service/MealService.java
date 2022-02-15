package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }


    public Meal get(int id) {
        if (repository.get(id).getUserId() == SecurityUtil.authUserId()) {
            return repository.get(id);
        }
        return null;
    }

    public boolean delete(int id) {
        if (repository.get(id).getUserId() == SecurityUtil.authUserId()) {
            return repository.delete(id);
        }
        return false;
    }

    public List<Meal> getAll(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return repository.getAll().stream().filter(meal ->
                DateTimeUtil.isBetweenHalfOpen(meal.getDateTime()
                        , LocalDateTime.of(startDate.getYear()
                        , startDate.getMonth()
                        , startDate.getDayOfMonth()
                        , startTime.getHour()
                        , startTime.getMinute())
                        , LocalDateTime.of(endDate.getYear()
                                , endDate.getMonth()
                                , endDate.getDayOfMonth()
                                , endTime.getHour()
                                , endTime.getMinute())
                        )).collect(Collectors.toList());
    }

}