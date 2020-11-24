package Commands;

import Users.IUsersRepository;
import Users.UserBot;

public abstract class AbstractCommand {

    protected final IUsersRepository usersBot;
    protected final String identifierCommand;
    protected final String descriptionCommand;

    public AbstractCommand(String identifier, String description, IUsersRepository usersBotOperation){
        identifierCommand = identifier;
        descriptionCommand = description;
        usersBot = usersBotOperation;
    }

    /**
     * @return является ли пользователь администратором.
     */
    protected boolean userIsAdmin(String identifier){
        return usersBot.getAdminIdentifier().replace(" ", "").equals(identifier);
    }

    public String getDescriptionCommand(){
        return descriptionCommand;
    }

    public String getIdentifierCommand(){
        return identifierCommand;
    }

    abstract public String getAnswer(UserBot user, String[] strings);


}
