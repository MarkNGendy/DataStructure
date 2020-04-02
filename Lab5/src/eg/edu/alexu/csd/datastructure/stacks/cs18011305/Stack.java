package eg.edu.alexu.csd.datastructure.stacks.cs18011305;

import java.util.LinkedList;

public class Stack implements IStack {
    LinkedList<Object> stack = new LinkedList<>();
    @Override
    public Object pop() {
        if (!(stack.isEmpty())) {
            Object element = stack.getFirst();
            stack.remove(stack.getFirst());
            return element;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Object peek() {
        if (!(stack.isEmpty())) {
            return stack.getFirst();
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public void push(Object element) {
        stack.addFirst(element);
    }

    @Override
    public boolean isEmpty() {
        return (stack.size() == 0);
    }

    @Override
    public int size() {
        return stack.size();
    }
}
