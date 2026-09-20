public class Btc	//binary tree class
{
private TreeNode root;	//instance variabl eof the root
private class TreeNode		//implementation of the node
{
private TreeNode left;	//left node
private TreeNode right;	//right node
private int data;		//data for the node

//implementation of the data using the constructor:
public TreeNode(int data)
{
this.data=data;
}
}
//implementation of the binary tree:
public void CreateBinaryTree()
{
//implementation of the data:
TreeNode a=new TreeNode(5);
TreeNode b=new TreeNode(52);
TreeNode c=new TreeNode(21);
TreeNode d=new TreeNode(3);
TreeNode e=new TreeNode(2);

//implementation of the dat into the nodes:
root=a;
a.left=b;
a.right=c;
b.left=d;
b.right=e;
}

//recursive implementation  of  the preorder:
public void preOrder(TreeNode root)
{
if(root==null)
{
return;
}
//if the root is not empty then data will be inserted into it and nodes
System.out.print(root.data+" ");
preOrder(root.left);
preOrder(root.right);
}
public static void main(String[] args)
{
//calling the methods  using the object of the main class
Btc bt=new Btc();
bt.CreateBinaryTree();
bt.preOrder(bt.root);
}
}