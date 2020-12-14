package Commands.Standart;

import Commands.AbstractCommand;
import Commands.LastUserQuery;
import Users.IUserRepository;
import Users.UserBot;

public class StopCommand extends AbstractCommand {

    public StopCommand(IUserRepository iUserRepository, LastUserQuery lastUserQuery) {
        super("/stop",
                "Stops communication with the administrator, ATTENTION! You are losing your verification.",
                iUserRepository, lastUserQuery);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String textMSG = "Stop.";
        return textMSG;
    }
}
