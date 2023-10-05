package ru.secteam.teamwork.model.cats;

import ru.secteam.teamwork.model.Animal;

import javax.persistence.Entity;


@Entity(name = "Cats")
public class Cat extends Animal {
    public Cat(long id, String name, int age, boolean gender, String shelter) {
        super(id, name, age, gender, shelter);
    }
}
