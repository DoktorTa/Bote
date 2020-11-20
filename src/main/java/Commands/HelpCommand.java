package Commands;

import Users.IUsersOperation;
import Users.UserBot;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(IUsersOperation usersBotOperation) {
        super("/help", "help command", usersBotOperation);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String answer = "";

        if (userIsAdmin(user.getIdentifier())) {
            answer = adminCommandList();
        } else {
            answer = userCommandList(new StringBuilder());
        }

        return answer;
    }

    private String adminCommandList(){
        StringBuilder help = new StringBuilder();
        help.append("/create_task").append(" - добавление задания. Для следующего поля введите '>^<'\n")
                .append(" Args: \n")
                .append("    Level task - уровень задания.\n")
                .append("    Points - количество балов за задания.\n")
                .append("    Text task - текст задания.\n")
                .append("    Task answer - варианты ответов, если их несколько то в списком: 1. Ответ1 2. Ответ2.\n")
                .append("    Correct answer - номера верных ответов. Если вариантов ответа нет то написать: '-1' \n")

                .append("/remove_task").append(" - удаление задания.\n")
                .append(" Args: \n")
                .append("    Number task - номер задания для удаления.\n")

                .append("/pending_ver").append(" - вывод списка не верифицированных пользователей.\n")

                .append("/ver").append(" - верификация пользователя из списка не верифицированных.\n")
                .append(" Args: \n")
                .append("    Identifier - идентификатор не верифицированного пользователя.\n")

                .append("/del").append(" - удаление пользователя из списка не верифицированных по идентификатору.\n")
                .append(" Args: \n")
                .append("    Identifier - идентификатор не верифицированного пользователя.\n");

        return userCommandList(help);
    }

    private String userCommandList(StringBuilder help){
        help.append("/lvl").append(" - показ всех заданий соответствующих уровню.\n")
                .append(" Args: \n")
                .append("    Level - уровень заданий.\n")

                .append("/num").append(" - показ задания под номером.\n")
                .append(" Args: \n")
                .append("    Number - номер задания.\n")

                .append("/all_tasks").append(" - показ всех номеров заданий.\n")

                .append("/start").append(" - старт он и в африке старт, ожидайте.\n")

                .append("/stop").append(" - добро пожаловать на все 3 доступные человеку оси координат, мы не будем скучать.\n");


        return help.toString();
    }
}
