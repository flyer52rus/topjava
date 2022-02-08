package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class EditMealServlet extends HttpServlet {
    private static final Logger log = getLogger(EditMealServlet.class);
    private static MealDAO mealDAO = MealDAOImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to edit doGet");
        response.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Meal meal = null;
        if (id >= 0) {
            meal = mealDAO.get(id);
        } else {
            meal = mealDAO.create();
        }

        request.setAttribute("meal", meal);
        getServletContext().getRequestDispatcher("/editMeal.jsp").forward(request, response);
    }
}