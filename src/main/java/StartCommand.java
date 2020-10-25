import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class StartCommand extends AbsCommand{

    public StartCommand() {
        super("/start", "Starting retrace msg to admin.");
    }


    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {



    }
}
