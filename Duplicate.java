import java.util.Scanner;
class Duplicate
{
public static void main(String[] args)
{
int a[]={1,3,6,8,2,4,3};
int i,j;
for(i=0;i<a.length;i++)
{
for(j=i+1;j<a.length;j++)
{
if(a[i]==a[j])
{
System.out.println("The duplicate number is:"+a[i]);
}

}
}



}
}