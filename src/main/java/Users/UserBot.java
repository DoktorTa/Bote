package Users;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public class UserBot {
    /*  The class is responsible for the bot user and is the same for all types of users. */

    private final User mUser;
    private final Chat mChat;
    public String identifier;

    public UserBot(User user, Chat chat){
        mUser = user;
        mChat = chat;
        setIdentifierUser();
    }

    private void setIdentifierUser(){
        // Name user + login user.
        identifier = mUser.getFirstName() + mUser.getUserName();
    }

    public User getUser(){
        return mUser;
    }

    public Chat getChat(){
        return mChat;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UserBot && ((UserBot) obj).getUser().equals(mUser);
    }
}
