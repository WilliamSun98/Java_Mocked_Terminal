package cmd;

import exceptions.CommandError;
import exceptions.DirectoryNotFoundError;
import fs.FileSystemManager;
import fs.MockedFileSystemManager;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PwdCommandTest {

  private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errStream = new ByteArrayOutputStream();
  private final PrintStream stdout = System.out;
  private final PrintStream stderr = System.err;
  private FileSystemManager fsm;
  private Command command;

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outStream));
    System.setErr(new PrintStream(errStream));
    fsm = new MockedFileSystemManager();
    command = new PwdCommand(fsm);
  }

  @Test
  public void rootPathTest() throws CommandError {
    command.setUp(new ArrayList<>());
    command.execute();
    Assert.assertEquals("/\n", outStream.toString());
  }

  @Test
  public void wrongArgumentInputTest() throws CommandError {
    List<String> args = new ArrayList<>();
    args.add("/test");
    command.setUp(args);
    command.execute();
    String res = "no arguments needed for pwd\n";
    Assert.assertEquals(res, errStream.toString());
  }

  @Test
  public void notRootPathTest() throws CommandError, DirectoryNotFoundError {
    String path = "/test/this/path/but/not/exist";
    fsm.changeCurrent(path);
    command.setUp(new ArrayList<>());
    command.execute();
    Assert.assertEquals(path + "\n", outStream.toString());
  }

  @After
  public void restore() {
    System.setOut(stdout);
    System.setErr(stderr);
  }

}
