package Users;


/** The class is responsible for the bot user and is the same for all types of users.*/
public class UserBot{

    private final String mChatId;
    private final String mIdentifier;

    public UserBot(String identifier, String chatId){
        mIdentifier = identifier;
        mChatId = chatId;
    }

    /**
     * @return идентификатор пользователя.
     */
    public String getIdentifier(){
        return mIdentifier;
    }

    /**
     * @return ай ди чата с пользователем телеграмма.
     */
    public String getChatId(){
        return mChatId;
    }



}
