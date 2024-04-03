# Linked List

## 237. Delete Node in a Linked List

There is a singly-linked list head and we want to delete a node node in it.

You are given the node to be deleted node. You will not be given access to the first node of head.

All the values of the linked list are unique, and it is guaranteed that the given node node is not the last node in the linked list.

Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:

- The value of the given node should not exist in the linked list.
- The number of nodes in the linked list should decrease by one.
- All the values before node should be in the same order.
- All the values after node should be in the same order.
  Custom testing:

- For the input, you should provide the entire linked list head and the node to be given node. node should not be the last node of the list and should be an actual node in the list.
- We will build the linked list and pass the node to your function.
- The output will be the entire list after calling your function. Leetcode [Link](https://leetcode.com/problems/delete-node-in-a-linked-list/description/).

#### Example 1:

![img1](https://assets.leetcode.com/uploads/2020/09/01/node1.jpg)

_Input:_ head = `[4,5,1,9]`, node = 5
_Output:_ `[4,1,9]`

**Explanation:** You are given the second node with value 5, the linked list should become `4 -> 1 -> 9 `after calling your function.

#### Example 2:

![img2](https://assets.leetcode.com/uploads/2020/09/01/node2.jpg)

_Input:_ head = `[4,5,1,9]`, node = 1
_Output:_ `[4,5,9]`

**Explanation:** You are given the third node with value 1, the linked list should become `4 -> 5 -> 9 `after calling your function.

**Constraints:**

- The number of the nodes in the given list is in the range `[2, 1000]`.
- -1000 <= Node.val <= 1000
- The value of each node in the list is unique.
- The node to be deleted is in the list and is not a tail node.

```java
/*
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```

## Reverse a Doubly LinkedList

```java


class Node {
    int data;
    Node next;
    Node prev;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Node(int data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}

public class Main {

    // Method to convert an array to an Doubly LinkedList
    public static Node convertArr2DLL(int[] arr) {
      Node head = new Node(arr[0]);
      Node prev = head;
      for (int i = 1; i < arr.length; i++) {
          Node temp = new Node(arr[i], null, prev);
          prev.next = temp;
          prev = temp;
      }
      return head;
    }

    // Method to iterate over a Doubly LinkedList
    public static void print(Node head) {
      Node temp = head;
      while (temp != null) {
          System.out.print(temp.data + " ");
          temp = temp.next;
      }
      System.out.println();
    }

    // reverse a Doubly LinkedList
    public  static Node reverseDLL(Node head) {
      if (head == null || head.next == null) {
        return head;
      }
      Node temp = head;
      Node back = null;
      while (temp != null) {
          back = temp.prev;
          temp.prev = temp.next;
          temp.next = back;
          temp = temp.prev;
      }
      return back.prev;
    }

    public static void main(String[] args) {
      int[] arr = {1, 2, 3, 4, 5};
      Node head = convertArr2DLL(arr);
      print(head);

      head = reverseDLL(head);
      print(head);
    }
}
```

## 2. Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself. Leetcode [Link](https://leetcode.com/problems/add-two-numbers/description/).

#### Example 1:

![img](https://assets.leetcode.com/uploads/2020/10/02/addtwonumber1.jpg)

_Input:_ l1 = `[2,4,3]`, l2 = `[5,6,4]`
_Output:_ `[7,0,8]`

**Explanation:** 342 + 465 = 807.

#### Example 2:

_Input:_ l1 = `[0]`, l2 = `[0]`
_Output:_ `[0]`

#### Example 3:

_Input:_ l1 = `[9,9,9,9,9,9,9]`, l2 = `[9,9,9,9]`
_Output:_ `[8,9,9,9,0,0,0,1]`

**Constraints:**

- The number of nodes in each linked list is in the range `[1, 100]`.
- 0 <= Node.val <= 9
- It is guaranteed that the list represents a number that does not have leading zeros.

```java


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;
        ListNode temp1 = l1, temp2 = l2;
        int carry = 0;
        while (temp1 != null || temp2 != null) {
            int sum = carry;
            if (temp1 != null) sum += temp1.val;
            if (temp2 != null) sum += temp2.val;

            ListNode newNode = new ListNode(sum % 10);
            carry = sum / 10;

            curr.next = newNode;
            curr = curr.next;
            if (temp1 != null) temp1 = temp1.next;
            if (temp2 != null) temp2 = temp2.next;
        }

        if (carry != 0) {
            ListNode newNode = new ListNode(carry);
            curr.next = newNode;
        }

        return dummyNode.next;
    }
}
```

## 328. Odd Even Linked List

Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in O(1) extra space complexity and O(n) time complexity. Leetcode [Link](https://leetcode.com/problems/odd-even-linked-list/description/).

#### Example 1:

![img1](https://assets.leetcode.com/uploads/2021/03/10/oddeven-linked-list.jpg)

_Input:_ head = `[1,2,3,4,5]`
_Output:_ `[1,3,5,2,4]`

#### Example 2:

![img2](https://assets.leetcode.com/uploads/2021/03/10/oddeven2-linked-list.jpg)

_Input:_ head = `[2,1,3,5,6,4,7]`
_Output:_ `[2,3,6,7,1,5,4]`

**Constraints:**

- The number of nodes in the linked list is in the range `[0, 104]`.
- -106 <= Node.val <= 106

```java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head, even = head.next, evenHead = head.next;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;

            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
```
