package User;

import org.telegram.telegrambots.meta.api.objects.User;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class RecoveryUsers {

//    public void SerializationUser(){
//
//    }

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
