package Stacks;

import java.util.*;

public class StackNotes {
    public static void main(String[] args) {
        
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }

        System.out.println("Stack: " + stack);
        System.out.println("Does contain 3: " + stack.contains(3));
        System.out.println("Last element: " + stack.peek());
        
        for (int i = 0; i < 2; i++) {
            stack.pop();
        }

        System.out.println("Stack: " + stack);
        System.out.println("Does contain 3: " + stack.contains(3));
        System.out.println("Last element: " + stack.peek());
    }
}