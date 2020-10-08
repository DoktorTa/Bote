package User;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public abstract class AbsGroup {
    protected final Set<UserBot> mGroupUsers;

    public AbsGroup(){
        mGroupUsers = new HashSet<>();
    }

    public boolean hasUserBot(User user) {
        return mGroupUsers.stream().anyMatch(a -> a.getUser().equals(user));
    }

    public Stream<UserBot> usersGroup() {
        return mGroupUsers.stream();
    }

    private boolean isDisplayedNameTaken(String name) {
        return mGroupUsers.stream().anyMatch(a -> Objects.equals(a.getDisplayedName(), name));
    }

    public abstract boolean addUserToGroup(UserBot userBot);
    public abstract boolean removeUserToGroup(User user);
}
