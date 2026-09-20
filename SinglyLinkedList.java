public class SinglyLinkedList
{
private ListNode head;

private static class ListNode
{
private int data;	//generic Type
private ListNode next;

public ListNode(int data)
{
this.data=data;
this.next=null;
}

}

//display
public void display()
{
ListNode current = head;
while(current != null)
{
System.out.print(current.data+ "-->");
current = current.next;
}
System.out.println("null");
}

//length
public int length()
{
int count = 0;
ListNode current = head;

if(head==null)
{
return 0;
}

while(current != null)
{
count++;
current = current.next;
}
return count;
}

//calling all the method in main method
public static void main(String[] args)
{
SinglyLinkedList sll=new SinglyLinkedList();
sll.head = new ListNode(10);
ListNode second = new ListNode(1);
ListNode third = new ListNode(8);
ListNode fourth = new ListNode(11);


//connectin in a chain
sll.head.next = second;
second.next = third;
third.next = fourth;
sll.display();
int a=sll.length();
System.out.print("Length:"+a);
}

}