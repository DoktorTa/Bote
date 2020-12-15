package DataBase;

import java.util.ArrayList;

public interface IDataBaseTask {

    /**
     * Возвращает задания выбранного уровня.
     * @param level уровень выборки.
     * @return номер и текст (первые 50 символов).
     */
    ArrayList<String> getTaskByLevel(String level);

    /**
     * Возвращает задание по его номеру.
     * @param number номер задания.
     * @return Полная информация о задании.
     */
    ArrayList<String> getTaskByNumber(String number);

    /**
     * Возвращает все задания.
     * @return номер и текст (первые 50 символов).
     */
    ArrayList<String> getAllTasks();

    /**
     *
     */
    String getAnswerTask(String num);

}
