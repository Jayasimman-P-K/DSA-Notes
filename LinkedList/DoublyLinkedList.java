package LinkedList;

class NodeList{
    int data; // Data stored in the NodeList
    NodeList next; // Reference to the next NodeList
    NodeList back; // Reference to the prev NodeList

    // Constructor to create a new NodeList with given data
    public NodeList(int data) {
        this.data = data;
        this.next = null; // Initialize next as null
        this.back = null; // Initialize prev as null
    }

    // Constructor to create a new NodeList with given data and next NodeList
    public NodeList(int data, NodeList next, NodeList back) {
        this.data = data;
        this.next = next;
        this.back = back;
    }
}

public class DoublyLinkedList { 

    // Method to convert an array to an Doubly LinkedList
    public static NodeList convertArr2DLL(int[] arr) {
        NodeList head = new NodeList(arr[0]);
        NodeList prev = head;
        for (int i = 1; i < arr.length; i++) {
            NodeList temp = new NodeList(arr[i], null, prev);
            prev.next = temp;
            prev = temp;
        }
        return head;
    }

    // Method to iterate over a Doubly LinkedList
    public static void print(NodeList head) {
        NodeList temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Method to remove head of an Doubly LinkedList
    public static NodeList removeHead(NodeList head) {
        if (head == null || head.next == null) return null;
        NodeList prev = head;
        head = head.next;
        head.back = null;
        prev.next = null;
        return head;
    }

    // Method to delete tail of the LinkedList
    public static NodeList removeTail(NodeList head) {
        if (head == null || head.next == null) return null;
        NodeList tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        NodeList prev = tail.back;
        prev.next = null;
        tail.back = null;
        return head;
    }

    // Method to remove Kth element of an Doubly LinkedList
    public static NodeList removeKth(NodeList head, int k) {
        if (head == null) return null;
        NodeList temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            if (count == k) break;
            temp = temp.next;
        }
        NodeList prev = temp.back;
        NodeList front = temp.next;
        if (prev == null && front == null) return null;
        else if (prev == null) return removeHead(head);
        else if (front == null) return removeTail(head);
        prev.next = front;
        front.back = prev;
        temp.next = null;
        temp.back = null;
        return head;  
    }

    // Method to insert a head of a LinkedList
    public static NodeList insertHead(NodeList head, int val) {
        NodeList newNode = new NodeList(val, head, null);
        head.back = newNode;
        return newNode;
    }

    // Method to insert a tail of a LinkedList
    public static NodeList insertTail(NodeList head, int val) {
        NodeList temp = head;
        while (temp.next != null) temp = temp.next;
        NodeList newNode = new NodeList(val, null, head);
        temp.next = newNode;
        return head;
    }

    // Method to insert before Kth element of an Doubly LinkedList
    public static NodeList insertBeforeKth(NodeList head, int k, int val) {
        if (k == 1) return insertHead(head, val);
        NodeList temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            if (count == k) break;
            temp = temp.next;
        }
        NodeList prev = temp.back;
        NodeList newNode = new NodeList(val, temp, prev);
        prev.next = newNode;
        temp.back = newNode;
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        NodeList head = convertArr2DLL(arr);
        print(head); // output: 1 2 3 4 5 6 7 

        head = removeHead(head);
        print(head); // output: 2 3 4 5 6 7

        head = removeTail(head);
        print(head); // output: 2 3 4 5 6 

        head = removeKth(head, 2);
        print(head); // output: 2 4 5 6

        head = insertHead(head, 1);
        print(head); // output: 1 2 4 5 6

        head = insertTail(head, 7);
        print(head); // output: 1 2 4 5 6 7

        head = insertBeforeKth(head, 6, 3);
        print(head); // output: 1 2 3 4 5 6 7
    }  
}
