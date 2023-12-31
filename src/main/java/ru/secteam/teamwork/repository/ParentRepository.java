package ru.secteam.teamwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.secteam.teamwork.model.Parent;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Репозиторий для связи с таблицей усыновителей из БД.
 */
public interface ParentRepository extends JpaRepository<Parent, Long> {
    /**
     * Поиск усыновителя по chatID.
     * ChatID у каждого пользователя индивидуальный.
     *
     * @param chatId
     * @return Parent
     */
    Parent findByChatId(Long chatId);

    /**
     * Поиск усыновителя по userName.
     * userName у каждого пользователя индивидуальный.
     *
     * @param userName
     * @return Parent
     */
    Parent findByUserName(String userName);

    Collection<Parent> findAllByReport(LocalDate nowDate);

}
