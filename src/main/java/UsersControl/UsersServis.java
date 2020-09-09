//package UsersControl;
//
//import org.telegram.telegrambots.meta.api.objects.Chat;
//import org.telegram.telegrambots.meta.api.objects.User;
//
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Set;
//import java.util.stream.Stream;
//
//public class UsersServis {
//    private final Set<UserBot> mUsers;
//
//    public UsersServis() {
//        mUsers = new HashSet<UserBot>();
//    }
//
//    public void setUserDisplayedName(User user, String name){
//
//        if (!isDisplayedNameTaken(name)){
//            mUsers.stream().filter(x -> x.getUser().equals(user)).forEach(x -> x.setDisplayedName(name));
//
//        }
//    }
//
//    private boolean isDisplayedNameTaken(String name) {
//        return mUsers.stream().anyMatch(x -> Objects.equals(x.getDisplayedName(), name));
//    }
//
//    public boolean removeUser(User user) {
//        return mUsers.removeIf(x -> x.getUser().equals(user));
//    }
//
//    public boolean addUser(UserBot anonymous) {
//        return mUsers.add(anonymous);
//    }
//
//    public boolean hasUser(User user) {
//        return mUsers.stream().anyMatch(x -> x.getUser().equals(user));
//    }
//
//    public String getDisplayedName(User user) {
//
//        UserBot user = mUsers.stream().filter(x -> x.getUser().equals(user)).findFirst().orElse(null);
//
//        if (user == null) {
//            return null;
//        }
//        return user.getDisplayedName();
//    }
//
//    public Stream<UserBot> anonymouses() {
//        return mUsers.stream();
//    }
//
//
//}
