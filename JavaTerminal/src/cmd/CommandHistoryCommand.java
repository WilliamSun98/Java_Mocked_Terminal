package cmd;

import containers.CommandHistory;
import java.util.List;

/**
 * This is the abstract class for all classes that deal with with the
 * history of commands typed by user.
 */
public abstract class CommandHistoryCommand implements Command {

  /**
   * This is the CommandHistory property that store the
   * history of all commands typed in by user
   */
  CommandHistory cmdHistory;

  /**
   * This is the constructor of the CommandHistoryCommand class it
   * instantiate the cmdHistory parameter which used for getting history of
   * command that user typed in.
   *
   * @param cmdHistory the history of the command that user typed in.
   */
  public CommandHistoryCommand(CommandHistory cmdHistory) {
    this.cmdHistory = cmdHistory;
  }

  /**
   * This is the execute method for finishing work of
   * CommandHistoryCommand.
   */
  @Override
  public void execute() {
  }

  /**
   * This method is used to print documentation of CommandHistoryCommand
   * Because CommandHistoryCommand is a abstract class, so it has no
   * documentation for it.
   */
  @Override
  public void getUsage() {
  }

  /**
   * This method is used to setup the parameters of CommandHistoryCommand.
   *
   * @param args this is the parameters got from user for
   * CommandHistoryCommand.
   */
  @Override
  public void setUp(List<String> args) {

  }
}
