package DataBase;

import java.util.ArrayList;

public interface IDataBaseUser {

    /**
     * Добавляет пользователя в базу данных.
     * @param identifier идентификатор добавляемого пользователя.
     * @param isAdmin флаг администратора (0 - если нет, 1 если да).
     * @param chatId айди чата с пользователем.
     */
    void addUserToDataBase(String identifier, String chatId, int points, int tasksComplete);

    /**
     * Удаляет пользователя из базы данных.
     * @param identifier идентификатор удаляемого пользователя.
     */
    void removeUserToDataBase(String identifier);

    /**
     * Возвращает идентификатор администратора.
     * @return идентификатор администратора.
     */
    String getAdminIdentifier();

    int getAllCompleteTaskUser(String identifier);

    /**
     * Возвращает пользователя по идентификатору.
     * @param identifier идентификатор пользователя.
     * @return пользователь бота.
     */
    ArrayList<String> getUserToDataBase(String identifier);
}
