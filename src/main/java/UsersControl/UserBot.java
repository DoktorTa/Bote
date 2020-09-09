package UsersControl;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
//import sun.net.ApplicationProxy;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UserBot {
    private static final Logger Logs = LogManager.getLogManager().getLogger(UserBot.class.getName());
    private static final String USER_CHAT_CANNOT_BE_NULL = "User or chat cannot be null!";

    private final User mUser;
    private final Chat mChat;
    private String mDisplayedName;

    public UserBot(User user, Chat chat){
        if (user == null || chat == null){
            Logs.log(Level.WARNING, USER_CHAT_CANNOT_BE_NULL);
            throw new IllegalStateException(USER_CHAT_CANNOT_BE_NULL);
        }
        mUser = user;
        mChat = chat;

    }

    public void setDisplayedName(String displayedName) {
        mDisplayedName = displayedName;
    }

    @Override
    public int hashCode() {
        return mUser.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UserBot && ((UserBot) obj).getUser().equals(mUser);
    }

    public User getUser() {
        return mUser;
    }

    public Chat getChat() {
        return mChat;
    }

    public String getDisplayedName() {
        return mDisplayedName;
    }
}


