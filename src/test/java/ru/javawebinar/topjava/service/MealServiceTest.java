package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal newMeal = new Meal(MEAL);
        Meal created = service.create(newMeal, 100000);
        newMeal.setId(created.getId());
        assertMatch(created, newMeal);
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL_ID2, USER_ID);
        assertMatch(meal, MEAL3);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(MEAL_ID - 1, USER_ID);
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), MEALS_WITHOUT_DELETED);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        service.delete(ANOTHER_USER_MEAL_ID, USER_ID);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> sorted = service.getBetweenDateTimes(
                LocalDateTime.of(2015, Month.MAY, 29, 0, 0, 0),
                LocalDateTime.of(2015, Month.MAY, 31, 0, 0, 0),
                USER_ID);
        assertMatch(sorted, MEALS_SORTED);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEALS);
    }

    @Test
    public void update() {
        Meal updated = MEAL2;
        updated.setDescription("Бранч");
        updated.setCalories(700);
        service.update(updated, USER_ID);
        assertMatch(service.get(MEAL_ID, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        Meal newMeal = new Meal(MEAL);
        newMeal.setId(ANOTHER_USER_MEAL_ID);
        service.update(newMeal, USER_ID);
    }
}