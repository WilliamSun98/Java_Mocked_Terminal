package test.containers;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import containers.DirectoryStack;
import exceptions.EmptyContainerError;
import fs.Directory;

public class TestDirectoryStack {

  @Test
  public void TestOneItem() {
    Directory parent = null;
    Directory aDir = new Directory("t1", parent);

    DirectoryStack aDirStack = new DirectoryStack();
    aDirStack.push(aDir);
    try {
      Assert.assertEquals(aDirStack.pop(), aDir);
    } catch (EmptyContainerError e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void TestMultiItem() {


    Directory parent = null;
    Directory aDir = new Directory("t1", parent);
    Directory aDir2 = new Directory("t2", aDir);
    Directory aDir3 = new Directory("t3", aDir);

    Directory bDir = new Directory("b1", parent);

    DirectoryStack allDir = new DirectoryStack();
    allDir.push(aDir);
    allDir.push(bDir);

    List<Directory> expected = new ArrayList<>();
    expected.add(bDir);
    expected.add(aDir);

    List<Directory> actual = new ArrayList<>();
    try {
      actual.add(allDir.pop());
    } catch (EmptyContainerError e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    try {
      actual.add(allDir.pop());
    } catch (EmptyContainerError e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    Assert.assertEquals(expected, actual);


  }


}
