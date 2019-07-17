package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {

    Meal create(Meal meal);

    Meal read(int id);

    Meal update(int id, Meal meal);

    void delete(int id);

    List<Meal> getList();
}