package eg.edu.alexu.csd.datastructure.stacks.cs18011305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    @Test
    public void testPush (){
        Stack stack = new Stack();
        stack.push(new Object());
        stack.push(new Object());
        stack.push(new Object());
        stack.push(new Object());
        stack.push(new Object());
        assertEquals(5, stack.size());
    }
    @Test
    public void testPop() {
        Stack stack = new Stack();
        stack.push(0);
        stack.push(1);
        stack.push(1);
        stack.push(2);
        stack.push(0);
        assertEquals(0, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertEquals(2, stack.size());
    }
    @Test
    public void testPeek() {
        Stack stack = new Stack();
        stack.push(0);
        stack.push(8);
        stack.push(5);
        assertEquals(5, stack.peek());
        assertEquals(3, stack.size());
        assertEquals(5, stack.pop());
        assertEquals(8, stack.peek());
    }
    @Test
    public void testisEmpty() {
        Stack stack = new Stack();
        assertTrue(stack.isEmpty());
        stack.push(8);
        stack.push(4);
        assertFalse(stack.isEmpty());
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
    }
    @Test
    public void testSize() {
        Stack stack = new Stack();
        assertEquals(0, stack.size());
        stack.push(5);
        assertEquals(1, stack.size());
        stack.pop();
        assertEquals(0, stack.size());
        stack.push(5);
        stack.push(4);
        stack.push(9);
        stack.push(9);
        stack.push(9);
        stack.push(9);
        assertEquals(6, stack.size());
    }
}
