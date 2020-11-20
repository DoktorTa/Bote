package Commands;

import Tasks.ITaskRepository;
import Users.IUsersOperation;

public abstract class AbstractTaskCommand extends AbstractCommand{

    protected final ITaskRepository iTaskRepository;
    protected final LastUserQuery lastUserQuery;


    public AbstractTaskCommand(String identifier, String description,
                               IUsersOperation usersBotOperation, ITaskRepository taskOperation,
                               LastUserQuery lastUserQuery1) {
        super(identifier, description, usersBotOperation);
        iTaskRepository = taskOperation;
        lastUserQuery = lastUserQuery1;
    }

}
