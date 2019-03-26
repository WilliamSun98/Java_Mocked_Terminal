package containers;

import fs.Directory;
import java.util.Stack;
import exceptions.EmptyContainerError;

/**
 * The DirectoryStack stores all references of Directories and it is a LIFO.
 */
public class DirectoryStack implements Container<Directory> {

  private  Stack<Directory> stack;

  public DirectoryStack() {
    stack = new Stack<>();
  }

  /**
   * The method will push the input directory into the top of the stack.
   * @param obj is the element to be stored.
   */
  @Override
  public void push(Directory obj) {
    stack.push(obj);
  }

  /**
   * The method will pop out the top directory in the stack.
   * @return is a directory which is on the top of the stack.
   * @throws EmptyContainerError if stack is empty.
   */
  @Override
  public Directory pop() throws EmptyContainerError {
    if (stack.empty()) {
      // needs one exception for empty stack
      throw new EmptyContainerError("Directory Stack is empty");
      // return null;
    } else {
      return stack.pop();
    }
  }
}
