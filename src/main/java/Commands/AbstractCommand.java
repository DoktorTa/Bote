package Commands;

import Users.IUserRepository;
import Users.UserBot;

public abstract class AbstractCommand {

    protected final LastUserQuery lastUserQuery;
    protected final String identifierCommand;
    protected final String descriptionCommand;
    protected final IUserRepository iUsersRepository;

    public AbstractCommand(String identifier, String description,
                           IUserRepository iUsersRepository1, LastUserQuery lastUserQuery1){
        identifierCommand = identifier;
        descriptionCommand = description;
        lastUserQuery = lastUserQuery1;
        iUsersRepository = iUsersRepository1;
    }

    public String getDescriptionCommand(){
        return descriptionCommand;
    }

    public String getIdentifierCommand(){
        return identifierCommand;
    }

    abstract public String getAnswer(UserBot user, String[] strings);


}
