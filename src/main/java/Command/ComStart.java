package Command;

public class ComStart extends AbsCommand {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AbsCommand;
    }

    @Override
    String executable(String commandArgs) {
        return "1";
    }
}