package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class MealRestController {

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public void getAllList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        log.info("getAllList");
        request.setAttribute("meals",
                MealsUtil.getTos(service.getAll(LocalDate.MIN, LocalTime.MIN, LocalDate.MAX, LocalTime.MAX)
                        , MealsUtil.DEFAULT_CALORIES_PER_DAY));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    public void getAllListFilterDT(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        log.info("getAllListFilterDT");
      //  List<String> listDT =new ArrayList<>();
        LocalDate startDate = request.getParameter("afterDate").isEmpty() ?
                LocalDate.MIN : LocalDate.parse(request.getParameter("afterDate"));
        LocalDate endDate = request.getParameter("beforeDate").isEmpty() ?
                LocalDate.MAX : LocalDate.parse(request.getParameter("beforeDate"));
        LocalTime startTime = request.getParameter("afterTime").isEmpty() ?
                LocalTime.MIN : LocalTime.parse(request.getParameter("afterTime"));
        LocalTime endTime = request.getParameter("beforeTime").isEmpty() ?
                LocalTime.MAX : LocalTime.parse(request.getParameter("beforeTime"));

        request.setAttribute("meals",
                MealsUtil.getTos(service.getAll(startDate, startTime, endDate, endTime), MealsUtil.DEFAULT_CALORIES_PER_DAY));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        final Meal meal = "create".equals(action) ?
                new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000, SecurityUtil.authUserId()) :
                service.get(getId(request));
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/mealForm.jsp").forward(request, response);

    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = getId(request);
        log.info("Delete {}", id);
        service.delete(id);
        response.sendRedirect("meals");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

}