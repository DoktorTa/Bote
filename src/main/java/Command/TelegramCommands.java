package Command;

import java.util.HashMap;
import java.util.logging.Logger;

public class TelegramCommands {
    /*Отвечает за все комманды которые поддерживает телеграмм версия бота,
    * Все комманды должны быть обьявленны здесь.*/

    public static final Logger LOGS = Logger.getLogger(TelegramCommands.class.getName());
    public HashMap<String, AbsCommand> Commands = new HashMap<String, AbsCommand>();

    public TelegramCommands(){
        initCommands();
    }

    /*Инициализация команд для использования происходит в этом методе*/
    private void initCommands(){
        Commands.put("/start", new ComStart());
        Commands.put("/bro", new ComBro());
    }

    /** Метод вызывает комманду с аргументами и возвращает ответ.
     * @param commandName - Имя комынды, со слешом.
     * @param commandArgs - Строка с аргументами команды
     */
    public String executeCommand(String commandName, String commandArgs){
        LOGS.info(commandName);
        AbsCommand command = Commands.get(commandName);
        if (command != null){
            String answerCommand = command.executable(commandArgs);
            return answerCommand;
        }
        else {
            return "Command not fond";
        }
    }
}
