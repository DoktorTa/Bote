package Commands;

import Users.IUsersOperation;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public class VerCommand extends AbsCommand{

    public VerCommand(IUsersOperation usersOperation){
        super("/ver", "Verification user", usersOperation);
    }

    @Override
    public String getAnswer(User user, Chat chat) {
        return null;
    }
}
