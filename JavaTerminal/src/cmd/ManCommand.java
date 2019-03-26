package cmd;

import java.util.List;
import java.util.Map;

/**
 * This class represents the ManCommand, it can get the documentation of a
 * certain command.
 */
public class ManCommand extends UsageCommand {

  /**
   * This is the command parameter
   */
  private Command command;
  /**
   * This is the Hashmap for mapping command name to an actual command
   */
  private Map<String, Command> cmdMap;

  /**
   * This is the constructor for the ManCommand It instantiate a HashMap.
   *
   * @param cmdMap is the HashMap used to map the Command Name to a actual
   * Command.
   */
  public ManCommand(Map<String, Command> cmdMap) {
    this.cmdMap = cmdMap;
  }

  /**
   * This method finish the functionality of getting documentation for
   * different commands.
   */
  @Override
  public void execute() {
    if (command != null) {
      command.getUsage();
    }
  }

  /**
   * This method print the documentation for the ManCommand.
   */
  @Override
  public void getUsage() {
    System.out.println();
    System.out.println("man CMD");
    System.out.println("\tPrint Documentation for CMD");
    System.out.println();
  }

  /**
   * This method is used to set up the command which the ManCommand will
   * print its documentation for.
   *
   * @param args is the commands names that typed in by user.
   */
  @Override
  public void setUp(List<String> args) {
    if (args == null || args.size() == 0) {
      System.err.println("What manual page do you want?");
      command = null;
    } else if (args.size() == 1) {
      command = cmdMap.get(args.get(0));
    } else {
      System.err.println("man: too many arguments");
      command = null;
    }
  }
}
