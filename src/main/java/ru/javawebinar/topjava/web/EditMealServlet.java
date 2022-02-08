package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.datasource.CountId;
import ru.javawebinar.topjava.datasource.DataSourceMemory;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class EditMealServlet extends HttpServlet {
    private static final Logger log = getLogger(EditMealServlet.class);
    private static List<Meal> meals = DataSourceMemory.getMeals();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to edit doGet");
        response.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Meal meal = null;
        if (id >= 0) {
            for (Meal mealSearch : meals) {
                if (mealSearch.getId() == id) {
                    meal = mealSearch;
                    break;
                }
            }
        } else {
            meal = new Meal(CountId.getInstance(),
                                             LocalDateTime.of(LocalDate.now().getYear(),
                                             LocalDate.now().getMonth(),
                                             LocalDate.now().getDayOfMonth(),
                                             LocalTime.now().getHour(),
                                             LocalTime.now().getMinute()),
                                  "",
                                     0);
            meals.add(meal);
        }

        request.setAttribute("meal", meal);
        getServletContext().getRequestDispatcher("/editMeal.jsp").forward(request, response);
    }
}
