package ru.secteam.teamwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.secteam.teamwork.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}