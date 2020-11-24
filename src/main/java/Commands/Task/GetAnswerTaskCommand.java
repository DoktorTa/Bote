package Commands.Task;

import Commands.LastUserQuery;
import Tasks.ITaskRepository;
import Users.IUsersRepository;
import Users.UserBot;

public class GetAnswerTaskCommand extends AbstractTaskCommand {


    public GetAnswerTaskCommand(IUsersRepository usersBotOperation, ITaskRepository taskOperation, LastUserQuery lastUserQuery1) {
        super("/answer", "Get answer for task.", usersBotOperation, taskOperation, lastUserQuery1);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String correctAnswer = iTaskRepository.getAnswerTask(strings[0]);
        String userAnswer = createUserAnswer(strings);

        if (checkAnswer(correctAnswer, userAnswer.substring(2).trim())){
            return "Correct answer!";
        }

        return "Correct answer: " + correctAnswer;
    }

    /**
     * Создает ответ пользователя как строку.
     * @param strings аргументы ответа пользователя.
     * @return строка ответа пользователя.
     */
    private String createUserAnswer(String[] strings){
        StringBuilder userAnswer = new StringBuilder();

        for (String answer: strings) {
            userAnswer.append(answer);
            userAnswer.append(" ");
        }

        return userAnswer.toString();
    }

    /**
     * Проверяет что ответ пользователя и правильный совпадают.
     * @param correctAnswer правильный ответ.
     * @param userAnswer пользовательский ответ.
     * @return совпадает или нет.
     */
    private boolean checkAnswer(String correctAnswer, String userAnswer){
        return correctAnswer.equals(userAnswer);
    }
}
