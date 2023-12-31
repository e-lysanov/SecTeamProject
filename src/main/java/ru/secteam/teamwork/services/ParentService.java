package ru.secteam.teamwork.services;

import ru.secteam.teamwork.model.Parent;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис реализует CRUD операции с таблицей усыновителей.
 * В дальнейшем при необходимости будут добавляться новые операции, помимо базовых CRUD
 */
public interface ParentService {
    /**
     * С - create. Метод добавление нового усыновитя в БД, если клиент не использовал бота.
     */
    Parent add(Parent parent);

    /**
     * R - read. Метод поиска усыновителя в БД по его chatID.
     */
    Parent get(Long chatId);

    /**
     * U - update. Метод для обновления данных по усыновителе в БД.
     * Поиск усыновителя, для которого требуется внести изменения, производится по chat ID.
     * Этот метод используется тогда, когда потенциальный усыновитель передает свои данные волонтеру для того, чтобы стать усыновителем
     * Волонтер не удаляет старые данные в таблице (chat id, user name и тд), а добавляет к ним личную ифнормацию человека
     */
    Parent update(Long chatId, Parent parent);

    /**
     * D - delete. Метод удаления усыновителя из БД.
     * Удаление производится по chat ID.
     * Метод будет возвращать информационное сообщение об удачном уделнии.
     */
    String delete(Long chatId);

    /**
     * Метод для получения списка всех усыновителей
     */
    List<Parent> allParents();


    /**
     * Метод добавления животного усыновителю
     */
    Parent addAnimal(Long chatId, Long id);


    /**
     * Метод добавления даты последнего отчета усыновителя.
     */
    Parent addDateOfReport(Long chatId, LocalDate date);

    /**
     * Метод отправки сообщений пользователям бота.
     */
    void sendMessageToParent(String userName, String textMessage);

    /**
     * Метод отправки поздравительного сообщения усыновителя после прохождения испытательного срока.
     */
    void sendCongratulatoryMessage(String userName);

    /**
     * Метод отправки сообщения о неудачном прохождении испытательного срока усыновителя.
     */
    void sendMessageAdoptionFailed(String userName);
}
