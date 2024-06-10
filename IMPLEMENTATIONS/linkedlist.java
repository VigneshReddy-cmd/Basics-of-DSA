// import java.util.Collection;
import java.util.HashSet;

public class linkedlist {
    static Node head;
    static Node last;

    private static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }

    }

    public void add_first(String data) {
        Node newnode = new Node(data);
        if (head == null) {
            head = newnode;
            last = newnode;
            return;
        }
        newnode.next = head;
        head = newnode;

    }

    public void add_last(String data) {
        Node newnode = new Node(data);
        if (head == null) {
            head = newnode;
            last = newnode;
            return;
        }
        last.next = newnode;
        last = newnode;
    }

    public void print() {
        if (head == null) {
            System.out.print("NULL");
            return;
        }
        Node newnode = head;
        while (newnode.next != null) {
            System.out.print(newnode.data + "->");
            newnode = newnode.next;
        }
        System.out.println(newnode.data + "->" + "Null");
    }

    public void add_pos(String data, int pos) {
        Node newnode = new Node(data);
        Node curr = head;
        for (int i = 1; i < pos - 1; i++) {
            curr = curr.next;
        }
        if (curr == last) {
            last.next = newnode;
            last = newnode;
        }
        Node temp;
        temp = curr.next;
        curr.next = newnode;
        newnode.next = temp; // swapping
    }

    public void delete_pos(int pos) {
        Node curr = head;
        for (int i = 1; i < pos - 1; i++) {
            curr = curr.next;
        }
        // swap the next.next function
        // Node temp;
        // temp=curr.next;
        curr.next = curr.next.next;
    }

    public void reverselist() {
        if (head == null || head.next == null)
            return;
        Node prev = head;
        Node curr = head.next;
        last = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            // update
            prev = curr;
            curr = next;
        }
        head.next = null;
        head = prev;
    }

    public void rursivereverse(Node curr, Node prev) {
        if (curr.next == null) {
            head = curr;
            curr.next = prev;
            return;
        }
        Node next = curr.next;
        curr.next = prev;
        rursivereverse(next, curr);

    }

    private void removecycle(Node head) {
        HashSet<Node> set = new HashSet<>();
        // Node prev = head;
        // while (!set.contains(prev)) {
        //     set.add(prev);
        //     prev = prev.next;
        // }
        // System.out.print(set);
        Node hare = head;
        // Node turtle = head;
        while(hare.next!=null)
        {
            if(set.contains(hare.next))
            {
                break;
            }
            set.add(hare);
            hare=hare.next;
        }
        // already contains 
        // then
        // make hare .next=null
        hare.next=null;

    }

    public static void main(String args[]) {
        linkedlist ll = new linkedlist();
        ll.add_first("c");
        ll.add_first("b");
        ll.add_first("a");
        ll.add_last("d");
        ll.add_last("e");
        ll.add_last("f");
        ll.add_last("g");
        ll.add_last("h");
        ll.add_last("i");
        // ll.add_last("h");
        // ll.print();
        // System.out.println("AFTER Adding A@2");
        // ll.add_pos("at2",2);
        // ll.print();
        // System.out.println("AFTER DELETING A@2");
        // ll.delete_pos(2);
        // ll.print();
        // System.out.println("AFTER DELETING A@5");
        // ll.delete_pos(5);
        // ll.print();
        // System.out.println("AFTER Adding A@5");
        // ll.add_pos("e",5);
        // ll.print();
        // ll.reverselist();
        // ll.print();
        // System.out.println("AFTER DELETING A@5");
        // ll.delete_pos(5);
        // ll.print();
        // System.out.println("AFTER Adding A@5");
        // ll.add_pos("f",5);
        // ll.print();
        // ll.reverselist();
        // ll.print();
        // ll.rursivereverse(head,null);
        // ll.print();

        // making circukar linked list from a point
        ll.print();
        Node temp = head;
        temp = temp.next.next.next;
        Node temp1 = head;
        while (temp1.next != null) {
            temp1 = temp1.next;
        }
        temp1.next = temp;
        // ll.print();
        // i had d
        // remove i-> d
        ll.removecycle(head);
         ll.print();
        // store the list in the hashset

    }
}
