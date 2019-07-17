package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoInMemoryImpl implements MealDao {

    private AtomicInteger idCounter = new AtomicInteger();

    private Map<Integer, Meal> meals;

    public MealDaoInMemoryImpl() {
        meals = new ConcurrentHashMap<>();
        mapInit();
    }

    private void mapInit() {
        meals.put(idCounter.incrementAndGet(), new Meal(idCounter.get(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.put(idCounter.incrementAndGet(), new Meal(idCounter.get(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.put(idCounter.incrementAndGet(), new Meal(idCounter.get(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.put(idCounter.incrementAndGet(), new Meal(idCounter.get(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.put(idCounter.incrementAndGet(), new Meal(idCounter.get(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.put(idCounter.incrementAndGet(), new Meal(idCounter.get(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public Meal create(Meal meal) {
        int id = idCounter.incrementAndGet();
        meal.setId(id);
        meals.put(id, meal);
        return meal;
    }

    @Override
    public Meal read(int id) {
        return meals.get(id);
    }

    @Override
    public Meal update(int id, Meal meal) {
        meal.setId(id);
        meals.put(id, meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    public List<Meal> getList() {
        return new ArrayList<>(meals.values());
    }
}