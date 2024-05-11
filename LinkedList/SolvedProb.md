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
        // dummy node approach
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

## 19. Remove Nth Node From End of List

Given the head of a linked list, remove the nth node from the end of the list and return its head. Leetcode [Link](https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/).

#### Example 1:

![img](https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg)

_Input:_ head = `[1,2,3,4,5]`, n = 2
_Output:_ `[1,2,3,5]`

#### Example 2:

_Input:_ head = `[1]`, n = 1
_Output:_ `[]`

#### Example 3:

_Input:_ head = `[1,2]`, n = 1
_Output:_ `[1]`

**Constraints:**

- The number of nodes in the list is sz.
- 1 <= sz <= 30
- 0 <= Node.val <= 100
- 1 <= n <= sz

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        for (int i = 0; i < n; i++) fast = fast.next;
        if (fast == null) return head.next;
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
```

## 206. Reverse Linked List

Given the head of a singly linked list, reverse the list, and return the reversed list. Leetcode [Link](https://leetcode.com/problems/reverse-linked-list/description/).

#### Example 1:

![img1](https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg)
Input: head = `[1,2,3,4,5]`
Output: `[5,4,3,2,1]`

#### Example 2:

![img2](https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg)
Input: head = `[1,2]`
Output: `[2,1]`

#### Example 3:

Input: head = `[]`
Output: `[]`

**Constraints:**

- The number of nodes in the list is the range `[0, 5000]`.
- -5000 <= Node.val <= 5000

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

// Itereative solution
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode temp = head;
        while (temp != null) {
            ListNode front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
    }
}
```

## 876. Middle of the Linked List

Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node. Leetcode [Link](https://leetcode.com/problems/middle-of-the-linked-list/description/).

#### Example 1:

![img1](https://assets.leetcode.com/uploads/2021/07/23/lc-midlist1.jpg)

_Input:_ head = `[1,2,3,4,5]`
_Output:_ `[3,4,5]`
**Explanation:** The middle node of the list is node 3.

#### Example 2:

![img2](https://assets.leetcode.com/uploads/2021/07/23/lc-midlist2.jpg)

_Input:_ head = `[1,2,3,4,5,6]`
_Output:_ `[4,5,6]`
**Explanation:** Since the list has two middle nodes with values 3 and 4, we return the second one.

**Constraints:**

- The number of nodes in the list is in the range `[1, 100]`.
- 1 <= Node.val <= 100

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
    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
```

## 234. Palindrome Linked List

Given the head of a singly linked list, return true if it is a
palindrome or false otherwise. Leetcode [Link](https://leetcode.com/problems/palindrome-linked-list/description/).

#### Example 1:

![img1](https://assets.leetcode.com/uploads/2021/03/03/pal1linked-list.jpg)

_Input:_ head = `[1,2,2,1]`
_Output:_ true

#### Example 2:

![img2](https://assets.leetcode.com/uploads/2021/03/03/pal2linked-list.jpg)

_Input:_ head = `[1,2]`
_Output:_ false

**Constraints:**

- The number of nodes in the list is in the range `[1, 105]`.
- 0 <= Node.val <= 9

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
// class Solution {
//     public boolean isPalindrome(ListNode head) {
//         if (head == null || head.next == null) return true;
//         ListNode slow = head, fast = head;
//         while (fast.next != null && fast.next.next != null) {
//             slow = slow.next;
//             fast = fast.next.next;
//         }
//         ListNode newNode = reverseLL(slow.next);
//         ListNode first = head, second = newNode;
//         while (second != null) {
//             if (first.val != second.val) {
//                 // reverseLL(newNode);
//                 return false;
//             }
//             first = first.next;
//             second = second.next;
//         }
//         // reverseLL(newNode);
//         return true;
//     }

//     public static ListNode reverseLL(ListNode head) {
//         ListNode prev = null, temp = head;
//         while (temp != null) {
//             ListNode front = temp.next;
//             temp.next = prev;
//             prev = temp;
//             temp = front;
//         }
//         return prev;
//     }
// }

class Solution {
    public boolean isPalindrome(ListNode head) {
        // find the middle element
        ListNode slow = head ;
        ListNode fast = head ;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second half of an linked list
        ListNode prev = null ;
        ListNode curr = slow ;
        while(curr!=null){
            ListNode later  = curr.next;
            curr.next = prev;
            prev=curr;
            curr=later ;
        }

        // compare each element in the first half and second half
        ListNode first = head ;
        ListNode second = prev;
        while(second!=null){
            if(first.val!=second.val){
                return false ;
            }
            first=first.next;
            second=second.next;
        }
        return true;
    }
}
```

## 160. Intersection of Two Linked Lists

Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:

![img](https://assets.leetcode.com/uploads/2021/03/05/160_statement.png)

The test cases are generated such that there are no cycles anywhere in the entire linked structure.

Note that the linked lists must retain their original structure after the function returns. Leetcode [Link](https://leetcode.com/problems/intersection-of-two-linked-lists/description/)

#### Example 1:

![img1](https://assets.leetcode.com/uploads/2021/03/05/160_example_1_1.png)

_Input:_ intersectVal = 8, listA = `[4,1,8,4,5]`, listB = `[5,6,1,8,4,5]`, skipA = 2, skipB = 3
_Output:_ Intersected at '8'
**Explanation:** The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as `[4,1,8,4,5]`. From the head of B, it reads as `[5,6,1,8,4,5]`. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.

- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.

#### Example 2:

![img2](https://assets.leetcode.com/uploads/2021/03/05/160_example_2.png)

_Input:_ intersectVal = 2, listA = `[1,9,1,2,4]`, listB = `[3,2,4]`, skipA = 3, skipB = 1
_Output:_ Intersected at '2'
**Explanation:** The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as `[1,9,1,2,4]`. From the head of B, it reads as `[3,2,4]`. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.

#### Example 3:

![img3](https://assets.leetcode.com/uploads/2021/03/05/160_example_3.png)

_Input:_ intersectVal = 0, listA = `[2,6,4]`, listB = `[1,5]`, skipA = 3, skipB = 2
_Output:_ No intersection
**Explanation:** From the head of A, it reads as `[2,6,4]`. From the head of B, it reads as `[1,5]`. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.

**Constraints:**

- The number of nodes of listA is in the m.
- The number of nodes of listB is in the n.
- 1 <= m, n <= 3 \* 104
- 1 <= Node.val <= 105
- 0 <= skipA < m
- 0 <= skipB < n
- intersectVal is 0 if listA and listB do not intersect.
- intersectVal == listA`[skipA]` == listB`[skipB]` if listA and listB intersect.

```java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode temp1 = headA, temp2 = headB;
        while (temp1 != temp2) {
            temp1 = (temp1 == null) ? headB : temp1.next;
            temp2 = (temp2 == null) ? headA : temp2.next;
        }
        return temp1;
    }
}
```

## 141. Linked List Cycle

Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false. Leetcode [Link](https://leetcode.com/problems/linked-list-cycle/description/).

#### Example 1:

![img1](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

_Input:_ head = `[3,2,0,-4]`, pos = 1
_Output:_ true
**Explanation:** There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

#### Example 2:

![img2](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

_Input:_ head = `[1,2]`, pos = 0
_Output:_ true
**Explanation:** There is a cycle in the linked list, where the tail connects to the 0th node.

#### Example 3:

![img3](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

_Input:_ head = `[1]`, pos = -1
_Output:_ false
**Explanation:** There is no cycle in the linked list.

**Constraints:**

- The number of the nodes in the list is in the range `[0, 104]`.
- -105 <= Node.val <= 105
- pos is -1 or a valid index in the linked-list.

```java

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
```

## 2095. Delete the Middle Node of a Linked List

You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.

The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.

For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively. Leetcode [Link](https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/).

#### Example 1:

![img](https://assets.leetcode.com/uploads/2021/11/16/eg1drawio.png)

_Input:_ head = `[1,3,4,7,1,2,6]`
_Output:_ `[1,3,4,1,2,6]`
**Explanation:**
The above figure represents the given linked list. The indices of the nodes are written below.
Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
We return the new list after removing this node.

#### Example 2:

![img](https://assets.leetcode.com/uploads/2021/11/16/eg2drawio.png)

_Input:_ head = `[1,2,3,4]`
_Output:_ `[1,2,4]`
**Explanation:**
The above figure represents the given linked list.
For n = 4, node 2 with value 3 is the middle node, which is marked in red.

#### Example 3:

![img](https://assets.leetcode.com/uploads/2021/11/16/eg3drawio.png)

_Input:_ head = `[2,1]`
_Output:_ `[2]`
**Explanation:**
The above figure represents the given linked list.
For n = 2, node 1 with value 1 is the middle node, which is marked in red.
Node 0 with value 2 is the only node remaining after removing node 1.

**Constraints:**

- The number of nodes in the list is in the range `[1, 105]`.
- 1 <= Node.val <= 105

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
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        fast = fast.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
```

## 142. Linked List Cycle II

Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list. Leetcode [Link](https://leetcode.com/problems/linked-list-cycle-ii/description/)

#### Example 1:

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

_Input:_ head = `[3,2,0,-4]`, pos = 1
_Output:_ tail connects to node index 1
**Explanation:** There is a cycle in the linked list, where tail connects to the second node.

#### Example 2:

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

_Input:_ head = `[1,2]`, pos = 0
_Output:_ tail connects to node index 0
**Explanation:** There is a cycle in the linked list, where tail connects to the first node.

#### Example 3:

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

_Input:_ head = `[1]`, pos = -1
_Output:_ no cycle
**Explanation:** There is no cycle in the linked list.

**Constraints:**

- The number of the nodes in the list is in the range` [0, 104]`.
- -105 <= Node.val <= 105
- pos is -1 or a valid index in the linked-list

```java

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
```

## 25. Reverse Nodes in k-Group

Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed. Leetcode [Link](https://leetcode.com/problems/reverse-nodes-in-k-group/description/).

#### Example 1:

![img](https://assets.leetcode.com/uploads/2020/10/03/reverse_ex1.jpg)
_Input:_ head = `[1,2,3,4,5]`, k = 2
_Output:_ `[2,1,4,3,5]`

#### Example 2:

![img](https://assets.leetcode.com/uploads/2020/10/03/reverse_ex2.jpg)

_Input:_ head = `[1,2,3,4,5]`, k = 3
_Output:_ `[3,2,1,4,5]`

**Constraints:**

- The number of nodes in the list is n.
- 1 <= k <= n <= 5000
- 0 <= Node.val <= 1000

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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head, prevNode = null;
        while (temp != null) {
            ListNode kthNode = findKthNode(temp, k);
            if (kthNode == null) {
                if (prevNode != null) prevNode.next = temp;
                break;
            }
            ListNode nextNode = (kthNode.next != null) ? kthNode.next : null;
            kthNode.next = null;
            reverseLL(temp);
            if (temp == head) {
                head = kthNode;
            } else {
                prevNode.next = kthNode;
            }
            prevNode = temp;
            temp = nextNode;
        }
        return head;
    }

    public static ListNode findKthNode(ListNode temp, int k) {
        k -= 1;
        while (temp != null && k > 0) {
            k--;
            temp = temp.next;
        }
        return temp;
    }

    public static void reverseLL(ListNode head) {
        ListNode temp = head, prev = null;
        while (temp != null) {
            ListNode front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
    }
}
```

## 61. Rotate List

Given the head of a linked list, rotate the list to the right by k places. Leetcode [Link](https://leetcode.com/problems/rotate-list/description/).

#### Example 1:

![img](https://assets.leetcode.com/uploads/2020/11/13/rotate1.jpg)

_Input:_ head = `[1,2,3,4,5]`, k = 2
_Output:_ `[4,5,1,2,3]`

#### Example 2:

![img](https://assets.leetcode.com/uploads/2020/11/13/roate2.jpg)

_Input:_ head = `[0,1,2]`, k = 4
_Output:_ `[2,0,1]`

**Constraints:**

- The number of nodes in the list is in the range `[0, 500]`.
- -100 <= Node.val <= 100
- 0 <= k <= 2 \* 109

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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            length++;
            tail = tail.next;
        }
        if (k % length == 0) return head;
        k %= length;
        tail.next = head;
        ListNode newTail = findNthNode(head, length - k);
        head = newTail.next;
        newTail.next = null;
        return head;
    }

    public static ListNode findNthNode(ListNode temp, int k) {
        int cnt = 1;
        while (temp != null) {
            if (cnt == k) return temp;
            cnt++;
            temp = temp.next;
        }
        return temp;
    }
}
```

## 21. Merge Two Sorted Lists

You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list. Leetcode [Link](https://leetcode.com/problems/merge-two-sorted-lists/description/).

#### Example 1:

![img](https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg)

_Input:_ list1 = `[1,2,4]`, list2 = `[1,3,4]`
_Output:_ `[1,1,2,3,4,4]`

#### Example 2:

_Input:_ list1 = `[]`, list2 = `[]`
_Output:_ `[]`

#### Example 3:

_Input:_ list1 = [], list2 =` [0]`
_Output:_ `[0]`

_Constraints:_

- The number of nodes in both lists is in the range `[0, 50]`.
- -100 <= Node.val <= 100
- Both list1 and list2 are sorted in non-decreasing order.

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode temp1 = list1, temp2 = list2;
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;
        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                temp.next = temp1;
                temp = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp = temp2;
                temp2 = temp2.next;
            }
        }
        if (temp1 != null) temp.next = temp1;
        else temp.next = temp2;
        return dummyNode.next;
    }
}
```
