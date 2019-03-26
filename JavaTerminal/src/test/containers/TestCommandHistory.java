package test.containers;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import containers.CommandHistory;
import exceptions.EmptyContainerError;


public class TestCommandHistory {

  @Test
  public void ContainerEmptyStringTest() {
    CommandHistory aHistory = new CommandHistory();
    aHistory.push("");

    String actual = "";
    try {
      actual = aHistory.pop();
    } catch (EmptyContainerError e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Assert.assertEquals("", actual);
  }

  @Test
  public void ContainerEmptyTest() {
    CommandHistory History = new CommandHistory();
    Assert.assertEquals(0, History.size());
  }

  @Test
  public void ContainerPopOneItemTest() {

    CommandHistory aHistory = new CommandHistory();
    aHistory.push("mkdir test1");

    String actual = null;
    try {
      actual = aHistory.pop();
    } catch (EmptyContainerError e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Assert.assertEquals(actual, "mkdir test1");

  }

  @Test
  public void ContainerPopMultiItemTest() {
    CommandHistory History = new CommandHistory();
    List<String> expected = new ArrayList<>();
    List<String> actual = new ArrayList<>();

    expected.add("mkdir test1");
    expected.add("pwd");
    expected.add("cd test1");

    for (String aCommand : expected) {
      History.push(aCommand);
    }

    while (History.size() != 0) {
      try {
        actual.add(History.pop());
      } catch (EmptyContainerError e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    assertEquals(expected, actual);

  }

}
