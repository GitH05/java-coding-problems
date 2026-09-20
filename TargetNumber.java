import java.util.Scanner;
public class TargetNumber
{
public static void main(String[] args)
{
int i,j,size,target,index1,index2;
System.out.println("Enter the size of the array");
Scanner s=new Scanner(System.in);
size=s.nextInt();
System.out.println("Only "+size+" elements ar allow");
int a[]=new int[size];

for(i=0;i<size;i++)
{
a[i]=s.nextInt();
}
System.out.print("The elements are:");
for(i=0;i<size;i++)
{
System.out.print(a[i]+" ");
}
//Now finding the target elements:
System.out.print("\nEnter the target elements:");
target=s.nextInt();
for(i=0;i<size;i++)
{
for(j=i+1;j<size;j++)
{
if(target==a[i] + a[j])
{
index1=i;
index2=j;
System.out.println("The numbers are:"+"["+a[i]+" ,"+a[j]+"]");
System.out.println("Index: "+"["+index1+","+index2+"]");
}
}
}

}
}