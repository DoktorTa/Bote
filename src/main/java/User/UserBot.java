package User;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.logging.Logger;

public class UserBot {
    private static final Logger LOG = Logger.getLogger(Anonymous.class.getName());
    private static final String USER_CHAT_CANNOT_BE_NULL = "User or chat cannot be null!";

    private final User mUser;
    private final Chat mChat;
    private String mDisplayedName;

    private int statusUser;

    public UserBot(User user, Chat chat) {
        if (user == null || chat == null) {
            LOG.info(USER_CHAT_CANNOT_BE_NULL);
            throw new IllegalStateException(USER_CHAT_CANNOT_BE_NULL);
        }
        mUser = user;
        mChat = chat;
    }

    public void setStatus(int status){
        statusUser = status;
    }

    @Override
    public int hashCode() {
        return mUser.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Anonymous && ((Anonymous) obj).getUser().equals(mUser);
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

    public void setDisplayedName(String displayedName) {
        mDisplayedName = displayedName;
        // TODO: Вернуть сериализацию!
        //this.saveUser();
    }

//    private void saveUser(){
//        RecoveryUsers r = new RecoveryUsers();
//        r.DeserializationUser(this);
//    }
}
