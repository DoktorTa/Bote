package Commands;

import Tasks.ITaskRepository;
import Users.IUsersOperation;
import Users.UserBot;

public class GetAnswerTaskCommand extends AbstractTaskCommand {


    public GetAnswerTaskCommand(IUsersOperation usersBotOperation, ITaskRepository taskOperation, LastUserQuery lastUserQuery1) {
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

    private String createUserAnswer(String[] strings){
        StringBuilder userAnswer = new StringBuilder();

        for (String answer: strings) {
            userAnswer.append(answer);
            userAnswer.append(" ");
        }

        return userAnswer.toString();
    }

    private boolean checkAnswer(String correctAnswer, String userAnswer){
        return correctAnswer.equals(userAnswer);
    }
}
