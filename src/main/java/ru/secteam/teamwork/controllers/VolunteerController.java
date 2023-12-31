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
import ru.secteam.teamwork.model.Parent;
import ru.secteam.teamwork.model.Shelter;
import ru.secteam.teamwork.model.Volunteer;
import ru.secteam.teamwork.services.ShelterService;
import ru.secteam.teamwork.services.VolunteerService;

import java.util.List;

/**
 * Контроллер сервиса волонтеров
 *
 * @see VolunteerService
 */
@Slf4j
@RestController
@RequestMapping("/volunteers")
public class VolunteerController {
    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    /**
     * @param volunteer
     * @return
     * @see VolunteerService#add(Volunteer)
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Добавление нового волонтера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )

            )
    })
    @PostMapping
    public Volunteer add(@Parameter(description = "Все параметры добавляемого волонтера") @RequestBody Volunteer volunteer) {
        log.info("Эндпоинт добавления волонтера выполнен");
        return volunteerService.add(volunteer);
    }

    /**
     * @param chatId
     * @return
     * @see VolunteerService#get(Long)
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получение информации о волонтере по ID",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )

            )
    })
    @GetMapping("/{chatId}")
    public ResponseEntity<Volunteer> get(@Parameter(description = "ID волонтера") @PathVariable Long chatId) {
        Volunteer volunteer = volunteerService.get(chatId);
        if (volunteer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.info("Эндпоинт поиска волонтера выполнен");
        return ResponseEntity.ok(volunteer);
    }

    /**
     * @param chatId
     * @param volunteer
     * @return
     * @see VolunteerService#update(Long, Volunteer)
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Изменение данных о волонтере",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )

            )
    })
    @PutMapping("/update/{chatId}")
    public ResponseEntity<Volunteer> update(@Parameter(description = "ID изменяемого волонтера") @PathVariable Long chatId,
                                            @Parameter(description = "Новые параметры волонтера для замены") @RequestBody Volunteer volunteer) {
        Volunteer savedVolunteer = volunteerService.update(chatId, volunteer);
        if (savedVolunteer == null) {
            return ResponseEntity.badRequest().build();
        } else {
            log.info("Эндпоинт обновления данных волонтера выполнен");
            return ResponseEntity.ok(savedVolunteer);
        }
    }

    /**
     * @param chatId
     * @return
     * @see VolunteerService#delete(Long)
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление волонтера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )

            )
    })
    @DeleteMapping("/{chatId}")
    public ResponseEntity delete(@Parameter(description = "ID удаляемого волонтера") @PathVariable Long chatId) {
        volunteerService.delete(chatId);
        log.info("Эндпоинт удаления волонтера выполнен");
        return ResponseEntity.ok().build();
    }

    /**
     * Эндпоинт для вывода всех волонтеров.
     *
     * @param
     * @return список всех волонтеров.
     * @see VolunteerService#allVolunteers() ()
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех волонтеров",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Parent.class)
                    )
            )
    })
    @GetMapping
    public List<Volunteer> allVolunteers() {
        log.info("Эндпоинт вывода списка волонтеров выполнен");
        return volunteerService.allVolunteers();
    }
}
