package Commands.Task;

import Commands.AbstractCommand;
import Commands.LastUserQuery;
import Tasks.ITaskRepository;
import Users.IUsersRepository;

public abstract class AbstractTaskCommand extends AbstractCommand {

    protected final ITaskRepository iTaskRepository;
    protected final LastUserQuery lastUserQuery;


    public AbstractTaskCommand(String identifier, String description,
                               IUsersRepository usersBotOperation, ITaskRepository taskOperation,
                               LastUserQuery lastUserQuery1) {
        super(identifier, description, usersBotOperation);
        iTaskRepository = taskOperation;
        lastUserQuery = lastUserQuery1;
    }

}
