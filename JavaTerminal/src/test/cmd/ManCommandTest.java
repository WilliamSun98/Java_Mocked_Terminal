package cmd;

import exceptions.CommandError;
import fs.FileSystemManager;
import fs.MockedFileSystemManager;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManCommandTest {

  private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errStream = new ByteArrayOutputStream();
  private final PrintStream stdout = System.out;
  private final PrintStream stderr = System.err;
  private Map<String, Command> map;
  private Command command;

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outStream));
    System.setErr(new PrintStream(errStream));
    map = new HashMap<>();
    command = new ManCommand(map);
  }

  @Test
  public void manExitTest() throws CommandError {
    List<String> args = new ArrayList<>();
    map.put("exit", new ExitCommand());
    args.add("exit");
    command.setUp(args);
    command.execute();
    String res = "\nexit\n\tQuit the program\n\n";
    Assert.assertEquals(res, outStream.toString());
  }

  @Test
  public void manManTest() throws CommandError {
    List<String> args = new ArrayList<>();
    map.put("man", new ManCommand(null));
    args.add("man");
    command.setUp(args);
    command.execute();
    String res = "\nman CMD\n\tPrint Documentation for CMD\n\n";
    Assert.assertEquals(res, outStream.toString());
  }

  @Test
  public void noParameterInputTest() throws CommandError {
    command.setUp(new ArrayList<>());
    command.execute();
    String res = "What manual page do you want?\n";
    Assert.assertEquals(res, errStream.toString());
  }

  @Test
  public void tooManyParameterInputTest() throws CommandError {
    List<String> args = new ArrayList<>();
    args.add("first");
    args.add("second");
    args.add("third");
    command.setUp(args);
    command.execute();
    String res = "man: too many arguments\n";
    Assert.assertEquals(res, errStream.toString());
  }

  @After
  public void restore() {
    System.setOut(stdout);
    System.setErr(stderr);
  }

}
