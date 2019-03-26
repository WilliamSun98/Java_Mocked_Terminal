package cmd;

import containers.CommandHistory;
import java.util.List;

/**
 * This represents the HistoryCommand that will deal with the history of
 * command typed by user.
 */
public class HistoryCommand extends CommandHistoryCommand {

  /**
   * This is the num of history record to shown
   */
  private int num;
  /**
   * This is the size of total history commands
   */
  private int size;

  /**
   * This is the constructor for thr HistoryCommand class that instantiate
   * the super class's cmdHistory property.
   *
   * @param cmdHistory is the history of command that typed in by user.
   */
  public HistoryCommand(CommandHistory cmdHistory) {
    super(cmdHistory);
  }

  /**
   * This method is used to finish the functionality of the HistoryCommand
   * by using the parameters set up by setUp method.
   */
  @Override
  public void execute() {
    for (int i = size - num; i < size; i++) {
      System.out.println(i + ". " + cmdHistory.getByIndex(i));
    }
  }

  /**
   * This is the method that prints the documentation for HistoryCommand.
   */
  @Override
  public void getUsage() {
    System.out.println();
    System.out.println("usage: HistoryCommand");
    System.out.println("\tGet user's input histories");
    System.out.println();
  }

  /**
   * This is the method that setUp the parameters that will be used by
   * HistoryCommand.
   *
   * @param args this is the parameters got from user for HistoryCommands.
   */
  @Override
  public void setUp(List<String> args) {
    this.size = cmdHistory.size();
    if (args.isEmpty()) {
      num = size;
    } else {
      num = Math.min(Integer.parseInt(args.get(0)), size);
    }
  }
}
