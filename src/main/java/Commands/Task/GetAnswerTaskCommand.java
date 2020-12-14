package Commands.Task;

import Commands.LastUserQuery;
import Tasks.ITaskRepository;
import Users.IUserRepository;
import Users.UserBot;

public class GetAnswerTaskCommand extends AbstractTaskCommand {


    public GetAnswerTaskCommand(IUserRepository iUserRepository, ITaskRepository taskOperation, LastUserQuery lastUserQuery1) {
        super("/answer", "Get answer for task.", iUserRepository, taskOperation, lastUserQuery1);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String correctAnswer = iTaskRepository.getAnswerTask(strings[0]);
        String userAnswer = createUserAnswer(strings);

        int next_task = Integer.parseInt(strings[0]) + 1;
        lastUserQuery.addQuery(user.getChatId(), "num " + next_task);

        if (checkAnswer(correctAnswer, userAnswer.substring(2).trim())){
            return "И это правильный ответ!\n" + "/all_tasks или /next";
        }

        return "Это уже не в какие ворота не лезет!. \nВот правильный ответ, а не твоя стрепня: "
                + correctAnswer + "\n /all_tasks or /next";
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
