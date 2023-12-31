package ru.secteam.teamwork.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.secteam.teamwork.model.Animal;
import ru.secteam.teamwork.services.AnimalService;

import java.util.List;

/**
 * Контроллер сервиса животных
 *
 * @see AnimalService
 */
@Slf4j
@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * Эндпоинт для добавления животного.
     * @param animal
     * @return Добавленное животное.
     * @see AnimalService#add(Animal)
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Добавление нового животного",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Animal.class)
                    )

            )
    })
    @PostMapping
    public Animal add(@Parameter(description = "Все параметры добавляемого животного") @RequestBody Animal animal) {
        log.info("Эндпоинт добавления животного выполнен");
        return animalService.add(animal);
    }

    /**
     * Эндпоинт для поиска животного.
     * @param id
     * @return Информация об искомому животному.
     * @see AnimalService#get(Long)
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получение информации о животном по ID",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Animal.class)
                    )

            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Animal> get(@Parameter(description = "ID животного")@PathVariable Long id) {
        Animal animal = animalService.get(id);
        if (animal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.info("Эндпоинт поиска животного выполнен");
        return ResponseEntity.ok(animal);
    }

    /**
     * Эндпоинт для обновления данных о животном
     * @param id
     * @param animal
     * @return Обновленные данные о животном.
     * @see AnimalService#update(Long, Animal)
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Изменение данных о животном",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Animal.class)
                    )

            )
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Animal> update(@Parameter(description = "ID изменяемого животного") @PathVariable Long id,
                                         @Parameter(description = "Новые параметры животного для замены") @RequestBody Animal animal) {
        Animal savedAnimal = animalService.update(id, animal);
        if (savedAnimal == null) {
            return ResponseEntity.badRequest().build();
        } else {
            log.info("Эндпоинт обновления данных животного выполнен");
            return ResponseEntity.ok(savedAnimal);
        }
    }

    /**
     * Эндпоинт для удаления животного.
     * @param id
     * @return Информационное сообщение об удачном удалении животного.
     * @see AnimalService#delete(Long)
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление животного",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Animal.class)
                    )

            )
    })
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Parameter(description = "ID удаляемого животного") @PathVariable Long id) {
        animalService.delete(id);
        log.info("Эндпоинт удаления животного выполнен");
        return ResponseEntity.ok().build();
    }

    /**
     * Эндпоинт для вывода списка всех животных
     * @return список всех животных
     * @see AnimalService#allAnimals()
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех животных",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Animal.class)
                    )

            )
    })
    @GetMapping
    public List<Animal> allAnimals() {
        log.info("Эндпоинт вывода списка животного выполнен");
        return animalService.allAnimals();
    }

}
