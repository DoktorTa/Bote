package Users;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

/** The class is responsible for the bot user and is the same for all types of users.
 *
 * {@value} identifier - идентификатор пользователя для использования в группах.
 * */
public class UserBot {

    private final User mUser;
    private final Chat mChat;
    public String identifier;

    public UserBot(User user, Chat chat){
        mUser = user;
        mChat = chat;
        setIdentifierUser();
    }

    /**
     * Задает identifier как "FirstNameUserName".
     */
    private void setIdentifierUser(){
        // Name user + login user.
        identifier = mUser.getFirstName() + mUser.getUserName();
    }

    /**
     * @return пользователь телеграмма.
     */
    public User getUser(){
        return mUser;
    }

    /**
     * @return чат с пользователем телеграмма.
     */
    public Chat getChat(){
        return mChat;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UserBot && ((UserBot) obj).getUser().equals(mUser);
    }
}
