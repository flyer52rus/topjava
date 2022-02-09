package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;

import static org.slf4j.LoggerFactory.getLogger;

public class EditMealServlet extends HttpServlet {
    private static final Logger log = getLogger(EditMealServlet.class);
    private static MealDao mealDAO = MealDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to edit doGet");
        int id = Integer.parseInt(request.getParameter("id"));
        Meal meal = null;
        if (id > 0) {
            meal = mealDAO.get(id);
        }
        request.setAttribute("isAdd", id == 0);
        request.setAttribute("id", id);
        request.setAttribute("meal", meal);
        getServletContext().getRequestDispatcher("/editMeal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to edit doPost");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (request.getParameter("cancel") == null && Boolean.valueOf(request.getParameter("isAdd"))) {
            mealDAO.save(new Meal (Integer.parseInt(request.getParameter("id")),
                            LocalDateTime.parse(request.getParameter("dateTime")),
                            request.getParameter("description"),
                            Integer.parseInt(request.getParameter("calories"))));
        } else if (request.getParameter("cancel") == null && !(Boolean.valueOf(request.getParameter("isAdd")))) {
            mealDAO.save(new Meal (Integer.parseInt(request.getParameter("id")),
                            LocalDateTime.parse(request.getParameter("dateTime")),
                            request.getParameter("description"),
                            Integer.parseInt(request.getParameter("calories"))));
        }

        mealDAO.getAll().sort(Comparator.comparing(Meal::getDateTime));
        response.sendRedirect("meals");
    }
}