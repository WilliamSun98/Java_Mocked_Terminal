package driver;

import cmd.CdCommand;
import cmd.ExitCommand;
import cmd.ManCommand;
import cmd.MkdirCommand;
import containers.CommandHistory;
import containers.DirectoryStack;
import exceptions.CommandError;
import fs.FileSystemManager;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import cmd.Command;
import cmd.EchoCommand;
import cmd.HistoryCommand;
import cmd.LsCommand;
import cmd.PopdCommand;
import cmd.PwdCommand;
import exceptions.NotValidCommandError;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cmd.CatCommand;
import cmd.PushdCommand;

/**
 * This class is used by JShell to parse the input string, so that we could
 * execute the exact command typed by user.
 *
 * The class will have a HashMap mapping all strings into commands.
 */
public class Parser {

  /**
   * This is the FileSystemManager for control FileSystemManager
   */
  private FileSystemManager fsm;
  /**
   * This is the stack for storing directories
   */
  private DirectoryStack stackd;
  /**
   * This is the container for storing commands
   */
  private CommandHistory cmdHistory;
  /**
   * This is the HashMap to get actual Command
   * class by using class name
   */
  private Map<String, Command> commandMap;

  public Parser(FileSystemManager fsm, DirectoryStack stackd,
      CommandHistory cmdHistory) {
    this.fsm = fsm;
    this.stackd = stackd;
    this.cmdHistory = cmdHistory;
    this.commandMap = initialCommandMap();
  }

  /**
   * The parse method will take a string as input, and then return a command
   * calling getACommand, and then set up the ipnuts are the command so that we
   * execute the command.
   *
   * @param cmd is a string representing a command.
   * @return a command object so that we can execute it.
   * @throws NotValidCommandError when command input is not acceptable.
   */
  Command parse(String cmd) throws NotValidCommandError {
    /*
     *  command is a list of input s.t the first element is a string command
     *  while all other elements are parameters for command class
     */
    Matcher matcher = Pattern.compile("([^\"]\\S*|\".+?\") *").matcher(cmd);
    List<String> command = new ArrayList<>();
    String part;
    while (matcher.find()) {
      part = matcher.group(1).trim();
      command.add(part);
    }
    if (command.isEmpty()) {
      return null;
    }

    return getACommand(command.get(0), command.subList(1, command.size()));

  }

  /**
   * The method will give out a command object based on its inputs which are
   * command name and command parameters.
   *
   * @param aCommand is a string of a command.
   * @param args is the arguments or parameters for the command.
   * @return a command object to execute.
   * @throws NotValidCommandError when command is not acceptable.
   */
  private Command getACommand(String aCommand,
      List<String> args) throws NotValidCommandError {
    if (!commandMap.containsKey(aCommand)) {
      throw new NotValidCommandError(
          "The Command " + aCommand + " is not valid");
    }

    Command command = this.commandMap.get(aCommand);
    try {
      command.setUp(args);
    } catch (CommandError e) {
      System.err.println(e.getMessage());
    }

    return command;
  }

  /**
   * The method will initialize the HashMap, if more commands added in the
   * future, then, we need to update HashMap here.
   *
   * @return a HashMap that is already set up.
   */
  private Map<String, Command> initialCommandMap() {
    // set a hash map for all the commands
    Map<String, Command> map = new HashMap<>();

    map.put("cat", new CatCommand(fsm));
    map.put("ls", new LsCommand(fsm));
    map.put("pwd", new PwdCommand(fsm));
    map.put("pushd", new PushdCommand(stackd, fsm));
    map.put("popd", new PopdCommand(stackd, fsm));
    map.put("history", new HistoryCommand(cmdHistory));
    map.put("echo", new EchoCommand(fsm));
    map.put("mkdir", new MkdirCommand(fsm));
    map.put("cd", new CdCommand(fsm));
    map.put("man", new ManCommand(map));
    map.put("exit", new ExitCommand());
    return map;
  }
}

