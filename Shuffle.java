import java.util.*;
class Shuffle
{
//shuflle logic
public static void shuffle(int arr[])
{
int temp,r,i;
for(i=arr.length-1;i>=0;i--)
{
r=(int) (Math.random() * (i+1));
temp=arr[r];
arr[r]=arr[i];
arr[i]=temp;
System.out.print(arr[i]+" ");
}
}

//main method
public static void main(String[] args)
{
int size,i;

Scanner s=new Scanner(System.in);
System.out.print("The size of the array:");
size=s.nextInt();
//array implementation
int arr[]=new int[size];

for(i=0;i<size;i++)
{
arr[i]=s.nextInt();
}
System.out.print("\nThe Shuffled elements are:");
//method call
shuffle(arr);
}
}