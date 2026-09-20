
import java.util.Scanner;
public class ZeroSubArray
{
public static void main(String[] args)
{
int i,j,size;
System.out.println("Enter the size of the array:");
Scanner s=new Scanner(System.in);
size=s.nextInt();
int a[]=new int[size];

for(i=0;i<size;i++)
{
a[i]=s.nextInt();
System.out.print(a[i]+" ");
}

//finding the zero sub array:
for(i=0;i<size;i++)
	{
int sum=0;
for(j=i;j<size;j++)
	{
sum +=a[j];
if(sum==0)
	{
System.out.println("Subarray:["+i+ "..."+ j+"]");
	}
	}
	}
}
}