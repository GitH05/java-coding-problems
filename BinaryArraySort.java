import java.util.Scanner;
class BinaryArraySort 
{
public static void main(String[] args)
{
int i,count=0,temp,size;
System.out.print("Enter the size of the array:");
Scanner s=new Scanner(System.in);
size=s.nextInt();
//implementing the binary array[example:0100110000111]:
int a[]=new int[size];
System.out.println("Enter the Binary Elements: example:0100110000111");
for(i=0;i<size;i++)
{
a[i]=s.nextInt();
}
System.out.print("\nThe Binary Elements:");
for(i=0;i<size;i++)
{
System.out.print(a[i]+" ");
}
//BinarySortArray:
for(i=0;i<size;i++)
{
if(a[i]==0)
{
temp=a[i];
a[i]=a[count];
a[count]=temp;
count++;
}
}
//BInarySortArray:
System.out.print("\nThe Binary Sort Array:");
for(i=0;i<size;i++)
{
System.out.print(a[i]+" ");
}
}
}
{ 0, 1, 2, 2, 1, 0, 0, 2, 0, 1, 1, 0 }