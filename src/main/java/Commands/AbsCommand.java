package Commands;

import Users.IUsersOperation;
import Users.UserBot;

public abstract class AbsCommand {

    protected final IUsersOperation usersBot;
    protected final String identifierCommand;
    protected final String descriptionCommand;

    public AbsCommand(String identifier, String description, IUsersOperation usersBotOperation){
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
