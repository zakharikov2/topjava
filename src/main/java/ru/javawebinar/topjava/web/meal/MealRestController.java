package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.web.SecurityUtil.*;

@Controller
public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    @Autowired
    public MealRestController(MealService mealService) {
        this.service = mealService;
    }

    public Meal create(Meal meal) {
        log.info("create meal {}", meal);
        return service.create(meal);
    }

    public void delete(int id) {
        log.info("delete id {}", id);
        service.delete(id);
    }

    public Meal get(int id) {
        log.info("get id {}", id);
        return service.get(id);
    }

    public List<MealTo> getAll() {
        log.info("getAll meals");
        return MealsUtil.getWithExcess(service.getAll(), authUserCaloriesPerDay());
    }

    public List<MealTo> getFilteredByDateAndTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("getFilteredByDate meals");
        return MealsUtil.getWithExcess(service.getAll(), authUserCaloriesPerDay()).stream()
                .filter(mealTo -> DateTimeUtil.isBetweenDate(mealTo.getDateTime().toLocalDate(), startDate, endDate))
                .filter(mealTo -> DateTimeUtil.isBetweenTime(mealTo.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());
    }

    public void update(Meal meal, int id) {
        log.info("update meal {}", meal);
        assureIdConsistent(meal, id);
        service.create(meal);
    }

}