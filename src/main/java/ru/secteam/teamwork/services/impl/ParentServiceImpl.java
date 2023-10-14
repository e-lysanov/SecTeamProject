package ru.secteam.teamwork.services.impl;

import org.springframework.stereotype.Service;
import ru.secteam.teamwork.model.Animal;
import ru.secteam.teamwork.model.Parent;
import ru.secteam.teamwork.repository.AnimalRepository;
import ru.secteam.teamwork.repository.ParentRepository;
import ru.secteam.teamwork.services.AnimalService;
import ru.secteam.teamwork.services.ParentService;
/**
 * Имплементация сервиса усыновителей
 * @see ParentService
 */
@Service
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;

    public ParentServiceImpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    /**
     * Добавление нового усыновителя.
     * Используется метод репозитория {@link ParentRepository#save(Object)}
     * @param parent
     * @return Добавлен усыновитель.
     * @see ParentService#add(Parent)
     */
    @Override
    public Parent add(Parent parent) {
        return parentRepository.save(parent);
    }

    /**
     * Получение информации по усыновителю через chat ID
     * Используетяс метод репозитория {@link ParentRepository#findById(Object)}
     * @param chatId
     * @return Вся нформация по искомому усыновителю.
     * @see ParentService#get(Long)
     */
    @Override
    public Parent get(Long chatId) {
        return parentRepository.findByChatId(chatId);
    }

    /**
     * Метод обновления ифнормации об усыновителе.
     * Создается новый объект усыновителя, ему присваивается переданный chat ID.
     * Этому объекту сохраняются переданная информация по усыновителю.
     * @param chatId
     * @param parent
     * @return Данные по усыновителю обновлены
     * @see ParentService#update(Long, Parent)
     */
    @Override
    public Parent update(Long chatId, Parent parent) {
        // создается новый объект усыновителя.
        // передаётся ему chat ID существующего усыновителя, которого необходимо отредактировать
        Parent savedParent = get(chatId);
        if (savedParent == null) {
            return null;
        }
        // передаются новые параметры для животного
        savedParent.setAge(parent.getAge());
        savedParent.setName(parent.getName());
        savedParent.setGender(parent.getGender());
        return parentRepository.save(savedParent);
    }

    /**
     * Метод удаления усыновителя.
     * Используется метод репозитория {@link ParentRepository#deleteById(Object)}
     * @param chatId
     * @return Информационное сообщение об успешном удалении усыновителя.
     * @see ParentService#delete(Long)
     */
    @Override
    public String delete(Long chatId) {
        parentRepository.deleteByChatId(chatId);
        return "Усыновитель удалён";
    }
}
