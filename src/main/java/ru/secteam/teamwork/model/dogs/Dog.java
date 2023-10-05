package ru.secteam.teamwork.model.dogs;


import ru.secteam.teamwork.model.Animal;

import javax.persistence.Entity;

/**
 * Класс для создания таблицы с кошками в БД.
 */
@Entity(name = "Dogs")
public class Dog extends Animal {


    public Dog(long id, String name, int age, boolean gender, String shelter) {
        super(id, name, age, gender, shelter);
    }
}
