package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static MealDao mealDAO = MealDaoInMemory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals doGet");
        request.setAttribute("mealsTo", MealsUtil.getMealsTo(mealDAO.getAll()));
        getServletContext().getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals doPost");
        request.setCharacterEncoding("UTF-8");

        String delete = request.getParameter("delete");
        String edit = request.getParameter("edit");
        String add = request.getParameter("add");
        String save = request.getParameter("save");
        String cancel = request.getParameter("cancel");
        // удаляем запись и уходим на форму meals
        if (delete != null) {
            mealDAO.delete(Integer.parseInt(delete));
            response.sendRedirect("meals");
        }
        // уходим на форму editMeal для редактирования Meal
        if (edit != null) {
            Integer id = Integer.parseInt(edit);
            Meal meal = mealDAO.get(id);
            request.setAttribute("id", id);
            request.setAttribute("meal", meal);
            getServletContext().getRequestDispatcher("/editMeal.jsp").forward(request, response);
        }
        // уходим на форму meals без редактирования Meal
        if (cancel != null) {
            response.sendRedirect("meals");
        }
        // уходим на форму meals с сохранением изменений
        if (save != null) {
            Integer id = request.getParameter("id").length() == 0 ?
                    null : Integer.parseInt(request.getParameter("id"));
            mealDAO.save(new Meal (id,
                            LocalDateTime.parse(request.getParameter("dateTime")),
                            request.getParameter("description"),
                            Integer.parseInt(request.getParameter("calories"))));
            response.sendRedirect("meals");
        }
        // уходим на форму editMeal для добавления новой записи Meal
        if (add != null) {
            Meal meal = mealDAO.get(null);
            request.setAttribute("meal", meal);
            getServletContext().getRequestDispatcher("/editMeal.jsp").forward(request, response);
        }





    }

}