package Commands;

import Users.IUsersOperation;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public abstract class AbsCommand {

    protected final IUsersOperation usersBot;
    protected final String identifierCommand;
    protected final String descriptionCommand;

    public AbsCommand(String identifier, String description, IUsersOperation usersBotOperation){
        identifierCommand = identifier;
        descriptionCommand = description;
        usersBot = usersBotOperation;
    }

    public String getDescriptionCommand(){
        return descriptionCommand;
    }

    public String getIdentifierCommand(){
        return identifierCommand;
    }

    abstract public String getAnswer(User user, Chat chat);


}
