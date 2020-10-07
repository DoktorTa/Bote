package User;

import org.telegram.telegrambots.meta.api.objects.User;

import java.io.*;

public class RecoveryUsers {

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
