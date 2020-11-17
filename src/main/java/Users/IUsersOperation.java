package Users;

import java.util.ArrayList;

public interface IUsersOperation {

    /**
     * Добавляет нового администратора.
     * @param newAdmin пользователь бота.
     */
    void addAdmin(UserBot newAdmin);

    /**
     * Возвращает идентификатор администратора.
     * @return идентификатор администратора.
     */
    String getAdminIdentifier();

    /**
     * Добавляет пользователя к не проверенным пользователям.
     * @param user - пользователь бота.
     */
    void addUserToNoVerifiedUsers(UserBot user);

    /**
     * Добавляет пользователя к проверенным пользователям.
     * @param user пользователь бота.
     */
    void addUserToVerifiedUsers(UserBot user);

    /**
     * Удаляет пользователя из группы не проверенных пользователей.
     * @param identifier идентификатор пользователя.
     */
    void removeUserToNoVerifiedUsers(String identifier);

    /**
     * Удаляет пользователя из группы проверенных пользователей.
     * @param identifier идентификатор пользователя.
     */
    void removeUserToVerifiedUsers(String identifier);

    /**
     * Возвращает пользователя по идентификатору.
     * @param identifier идентификатор пользователя.
     * @return пользователь соответствующий идентификатору.
     */
    ArrayList<String> getUserFromVerifiedUsers(String identifier);

    /**
     * Поиск пользователя по идентификатору среди не проверенных пользователей.
     * @param identifier идентификатор пользователя.
     * @return пользователь бота.
     */
    UserBot searchUserInNoVerifiedUsers(String identifier);

    /**
     * Возвращает список не проверенных пользователей в виде строки.
     * @return строка из 10 не проверенных пользователей.
     */
    String getUsersGroupString();
}
