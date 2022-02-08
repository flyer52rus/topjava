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
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class DeleteMealServlet extends HttpServlet {
    private static final Logger log = getLogger(DeleteMealServlet.class);
    private static List<Meal> meals = DataSourceMemory.getMeals();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to delete doGet");
        int id = Integer.parseInt(request.getParameter("id"));
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId() == id) {
                meals.remove(i);
                break;
            }
        }

        request.setAttribute("mealsTo", ListMealsToAllTime.getMealsTo());
        getServletContext().getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
