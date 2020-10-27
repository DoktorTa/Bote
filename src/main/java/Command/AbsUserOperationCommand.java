package Command;

import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * Класс предоставлявший стандартные методы и поля для команд взаимодействующих с группам пользователей.
 * {@value} nVerUsersGroup группа прошедших верификацию пользователей.
 * {@value} nNoVerUsersGroup группа не прошедших верификацию пользователей.
 */
abstract public class AbsUserOperationCommand extends AbsCommand{
    protected final VerUserBot mVerUsersGroup;
    protected final NoVerUserBot mNoVerUsersGroup;

    public AbsUserOperationCommand(String commandIdentifier, String description, VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super(commandIdentifier, description);
        mVerUsersGroup = verUsersGroup;
        mNoVerUsersGroup = noVerUsersGroup;
    }

    /**
     *
     * @param user пользователь.
     * @param chat чат с пользователем.
     * @return является ли пользователь администратором.
     */
    protected boolean userIsAdmin(User user, Chat chat){
        return mVerUsersGroup.getAdmin().equals(new UserBot(user, chat));
    }
}
