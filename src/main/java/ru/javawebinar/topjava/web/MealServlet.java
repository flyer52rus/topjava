package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.datasource.DataSourceMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.ListMealsToAllTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static List<Meal> meals = DataSourceMemory.getMeals();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals doGet");
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("mealsTo", ListMealsToAllTime.getMealsTo());
        getServletContext().getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals doPost");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId() == id) {
                String[] dateTimeArray = request.getParameter("dateTime")
                        .replace('T', '-')
                        .replace(':', '-')
                        .split("-");
                LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(dateTimeArray[0]),
                                                               Integer.parseInt(dateTimeArray[1]),
                                                               Integer.parseInt(dateTimeArray[2]),
                                                               Integer.parseInt(dateTimeArray[3]),
                                                               Integer.parseInt(dateTimeArray[4]));
                meals.get(i).setDateTime(localDateTime);
                meals.get(i).setDescription(request.getParameter("description"));
                meals.get(i).setCalories(Integer.parseInt(request.getParameter("calories")));
                break;
            }
        }

        Collections.sort(meals, Comparator.comparing(Meal::getDateTime));
        request.setAttribute("mealsTo", ListMealsToAllTime.getMealsTo());
        getServletContext().getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
