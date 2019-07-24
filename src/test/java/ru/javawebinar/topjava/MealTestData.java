package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;
    public static final int MEAL_ID2 = START_SEQ + 7;

    public static final int USER_ID = 100000;
    public static final int ANOTHER_USER_MEAL_ID = 100100;

    public static final Meal MEAL = new Meal(null, LocalDateTime.of(2019, Month.MAY, 30, 10, 0), "Завтрак", 500);

    public static final Meal MEAL2 = new Meal(MEAL_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);

    public static final Meal MEAL3 = new Meal(MEAL_ID2, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

    public static final List<Meal> MEALS = Arrays.asList(

            new Meal(START_SEQ + 7, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
            new Meal(START_SEQ + 6, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(START_SEQ + 5, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(START_SEQ + 4, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(START_SEQ + 3, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(START_SEQ + 2, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500)
//            new Meal(START_SEQ + 8, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 700),
//            new Meal(START_SEQ + 9, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1200)
    );

    public static final List<Meal> MEALS_SORTED = Arrays.asList(
            new Meal(START_SEQ + 4, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(START_SEQ + 3, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(START_SEQ + 2, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500)
    );

    public static final List<Meal> MEALS_WITHOUT_DELETED = Arrays.asList(
            new Meal(START_SEQ + 7, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
            new Meal(START_SEQ + 6, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(START_SEQ + 5, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(START_SEQ + 4, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(START_SEQ + 3, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000)
//            new Meal(START_SEQ + 8, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 700),
//            new Meal(START_SEQ + 9, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1200)
    );

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "user_id");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "user_id").isEqualTo(expected);
    }
}