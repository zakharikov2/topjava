
package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealDao meals = new MealDaoInMemoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        List<MealTo> mealsWithExcess = MealsUtil.getWithExcess(meals.getList(), 2000);
        req.setAttribute("meals", mealsWithExcess);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("dateTime") != null && req.getParameter("calories") != null && req.getParameter("description") != null) {
            if (!req.getParameter("idToUpdate").equals("") && meals.read(Integer.parseInt(req.getParameter("idToUpdate"))) != null) {
                meals.update(
                        Integer.parseInt(req.getParameter("idToUpdate")),
                        new Meal(
                                LocalDateTime.parse(req.getParameter("dateTime")),
                                req.getParameter("description"),
                                Integer.parseInt(req.getParameter("calories")
                                )));
            } else {
                meals.create(new Meal(
                        LocalDateTime.parse(req.getParameter("dateTime")),
                        req.getParameter("description"),
                        Integer.parseInt(req.getParameter("calories")
                        )));
            }
        } else if (req.getParameter("idToDelete") != null && meals.read(Integer.parseInt(req.getParameter("idToDelete"))) != null) {
            int id = Integer.parseInt(req.getParameter("idToDelete"));
            meals.delete(id);
        }
        resp.sendRedirect("meals");
    }
}