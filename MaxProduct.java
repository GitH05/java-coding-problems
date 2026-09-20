import java.util.Scanner;
class MaxProduct
{
public static void main(String[] args)
{
int i,j;
int a[]={1,9,4,2,-5,7,22,-18};
int maxProduct1=a[0];
int maxProduct2=a[1];
for(i=0;i<a.length;i++)
{
for(j=i+1;j<a.length;j++)
{
if((a[i]*a[j])>maxProduct1*maxProduct2)
{
maxProduct1=a[i];
maxProduct2=a[j];
}
}
}
System.out.println("The max pair of the integer are:"+"["+maxProduct1+","+maxProduct2+"]");

}
}