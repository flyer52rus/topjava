package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.util.ListMealsToAllTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static MealDAO mealDAO = MealDAOImpl.getInstance();

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
        String[] dateTimeArray = request.getParameter("dateTime")
                .replace('T', '-')
                .replace(':', '-')
                .split("-");
        LocalDateTime localDT = LocalDateTime.of(Integer.parseInt(dateTimeArray[0]),
                Integer.parseInt(dateTimeArray[1]),
                Integer.parseInt(dateTimeArray[2]),
                Integer.parseInt(dateTimeArray[3]),
                Integer.parseInt(dateTimeArray[4]));
        mealDAO.edit(id,
                localDT,
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        mealDAO.sort();
        request.setAttribute("mealsTo", ListMealsToAllTime.getMealsTo());
        getServletContext().getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}