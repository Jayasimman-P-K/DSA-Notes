package LinkedList;

// In a LinkedList, each element is represented by a node.
// A node contains two parts: data and a reference (or pointer) to the next node in the sequence.

// Node class:
class Node{
    int data; // Data stored in the node
    Node next; // Reference to the next node

    // Constructor to create a new node with given data
    public Node(int data) {
        this.data = data;
        this.next = null; // Initialize next as null
    }

    // Constructor to create a new node with given data and next node
    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class LinkedList {
    
    // Method to convert an array to an LinkedList
    public static Node convertArrToLL(int[] arr) {
        Node head = new Node(arr[0]);
        Node mover = head;
        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            mover.next = temp;
            mover = temp;
        }
        return head;
    }

    // Method to iterate over a LinkedList
    public static void print(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Method to find a length of a LinkedList
    public static int lengthOfLL(Node head) {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // Method to remove head of an LinkedList
    public static Node removeHead(Node head) {
        if (head == null) return head;
        head = head.next;
        return head;
    }

    // Method to remove the tail of the LinkedList
    public static Node removeTail(Node head) {
        if (head == null || head.next == null) return null;
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        return head;
    }

    // Method to remove the Kth element of the LinkedList
    public static Node removeK(Node head, int k) {
        if (head == null) return head;
        if (k == 1) return head.next;
        int count = 0;
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            count++;
            if (count == k) {
                prev.next = temp.next;
                break;
            }
            prev = temp;
            temp = temp.next;
        }
        return head;
    }

    // Method to delete the given element of the LinkedList
    public static Node removeEl(Node head, int el) {
        if (head == null) return head;
        if (head.data == el) return head.next;
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            if (temp.data == el) {
                prev.next = temp.next;
                break;
            }
            prev = temp;
            temp = temp.next;
        }
        return head;
    }

    // Method to insert an element in the head of a LinkedList
    public static Node insertHead(Node head, int el) {
        Node temp = new Node(el, head);
        return temp;
    }

    // Method to insert an element in the Tail of a LinkedList
    public static Node insertTail(Node head, int el) {
        if (head == null) return new Node(el);
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(el);
        return head;
    }

    // Method to insert an element in the Kth position of a LinkedList
    public static Node insertElPosition(Node head, int el, int k) {
        if (head == null) {
            if (k == 1) return new Node(el);
            else return null;
        }
        if (k == 1) {
            return new Node(el, head);
        }
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            if (count == k-1) {
                Node newNode = new Node(el, temp.next);
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }
        return head;
    }

    // Method to insert an element in the Kth position of a LinkedList
    public static Node insertElValue(Node head, int el, int val) {
        if (head == null) return null;
        if (head.data == val) {
            return new Node(el, head);
        }
        Node temp = head;
        while (temp != null) {
            if (temp.next.data == val) {
                Node newNode = new Node(el, temp.next);
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }
        return head;
    }


    public static void main(String[] args) {
        
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        Node head = convertArrToLL(arr);
        print(head); // output: 1 2 3 4 5 6 7
        System.out.println(lengthOfLL(head)); // output: 7

        // Deletion processes in LinkedList
        head = removeHead(head);
        print(head); // output: 2 3 4 5 6 7
        System.out.println(lengthOfLL(head)); // output: 6 

        head = removeTail(head);
        print(head); // output: 2 3 4 5 6
        System.out.println(lengthOfLL(head)); // output: 5
        
        head = removeK(head, 4);
        print(head); // output: 2 3 4 6
        System.out.println(lengthOfLL(head)); // output: 4  

        head = removeEl(head, 3);
        print(head); // output: 2 4 6
        System.out.println(lengthOfLL(head)); // output: 3  

        // Insertion processes in LinkedList
        head = insertHead(head, 1);
        print(head); // output: 1 2 4 6
        System.out.println(lengthOfLL(head)); // output: 4  

        head = insertTail(head, 7);
        print(head); // output: 1 2 4 6 7
        System.out.println(lengthOfLL(head)); // output: 5  

        head = insertElPosition(head, 5, 4);
        print(head); // output: 1 2 4 6 7
        System.out.println(lengthOfLL(head)); // output: 6  

        head = insertElValue(head, 3, 4);
        print(head); // output: 1 2 3 4 6 7
        System.out.println(lengthOfLL(head)); // output: 7  
    }
}
