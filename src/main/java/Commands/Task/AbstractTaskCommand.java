package Commands.Task;

import Commands.AbstractCommand;
import Commands.LastUserQuery;
import Tasks.ITaskRepository;
import Users.IUserRepository;

public abstract class AbstractTaskCommand extends AbstractCommand {

    protected final ITaskRepository iTaskRepository;

    public AbstractTaskCommand(String identifier, String description, IUserRepository iUserRepository,
                               ITaskRepository taskOperation, LastUserQuery lastUserQuery) {
        super(identifier, description, iUserRepository, lastUserQuery);
        iTaskRepository = taskOperation;
    }

}
