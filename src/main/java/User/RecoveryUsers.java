package User;

import org.telegram.telegrambots.meta.api.objects.User;

import java.io.*;

public class RecoveryUsers {
    /*
    * Класс отвечает за сохранение пользователей.
    *
    * Существует непроверенный баг в следствии которого после десериализации файл не отчичаються и туда записываются
    * те же юзеры, для того чтобы сделать поведение более предсказуемым можно добавить еще и сериализацию имен и групп
    * юзеров чтобы полсе десиарелизации, вопервых проверить что все юзеры востановленны, вовторых с чистой душой
    * отчистить файл, очевидно что сериализация юзеров должна происходить перед отключением бота а не после регистрации
    * каждого, ибо это поможет сконцентрировать все действия связаные с сериализацией и десеарилизацией в одном месте.
    * */

    public Anonymous SerializationUser() throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Users.dat")))
        {
            Anonymous user = (Anonymous)ois.readObject();
            return user;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    public void DeserializationUser(Anonymous user){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Users.dat", true)))
        {
            oos.writeObject(user);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }



}
