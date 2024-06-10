package Trees;
import java.util.*;
class Node<T>
{
    T val;
    Node<T> left;
    Node<T> right;
    public Node(T val,Node<T> left,Node<T> right)
    {
      this.val=val;
      this.left=left;
      this.right=right;
    }
    public Node(T val)
    {
      this.val=val;
      this.left=null;
      this.right=null;
    }
}
class BST<T extends Comparable<T>>
{
   private Node<T> root;
   public BST()
   {
      this.root=null;
   }
   public void insert(T val)
   {
     root=insertNode(root,val);
   }
   private Node<T> insertNode(Node<T> root,T val)
   {
       if(root==null)
       {
        Node<T> nn=new Node<>(val);
        return nn;
       }
       if(root.val.compareTo(val)>0)
       {
          root.left=insertNode(root.left,val);
       }
       else
       {
       root.right=insertNode(root.right,val);
       }
       return root;
   }
  public void search(T val)
  {
     if(do_search(root,val))
     {
     System.out.println("Yes");
     }
     else
     {
     System.out.println("No");
     }
  }
  private boolean do_search(Node<T> root,T val)
  {
     if(root==null)
     {
       return false;
     }
     if(root.val==val)
     {
     return true;
     }
     if(root.val.compareTo(val)>0)
     {
       return do_search(root.left,val);
     }
     else 
     {
      return do_search(root.right,val);
     }
  }
  public void delete(T val)
  {
    deleteNode(root,val);
  }
  private Node<T> deleteNode(Node<T> root,T val)
  {  
     if(root==null)
     {
       return root;
     }
     if(root.val.compareTo(val)>0)
     {
      root.left=deleteNode(root.left,val);
     }
     else  if(root.val.compareTo(val)<0)
     {
       root.right=deleteNode(root.right,val);
     }
     else
     {
       if(root.left==null && root.right==null)
       {
         return null;
       }
       else if(root.left==null)
       {
        return root.right;
       }
       else if(root.right==null)
       {
        return root.left;
       }
       else{
          root.val=max(root.left);
          deleteNode(root.left,root.val);
       }
     }
     return root;
  }
  private T max(Node<T> root)
  {
    T val=null;
    while(root!=null)
    {
    val=root.val;
       root=root.right;
    }
    return val;
  }
  public void preorder()
   {
     do_preorder(root);
     System.out.print("\n");
   }
   private void do_preorder(Node<T> root)
   {
     if(root==null){
     return;
     }
     System.out.print(root.val+" ");
     do_preorder(root.left);
     do_preorder(root.right);
     return;
   }
    public void inorder()
   {
     do_inorder(root);
     System.out.print("\n");
   }
   private void do_inorder(Node<T> root)
   {
     if(root==null){
     return;
     }
     do_inorder(root.left);
     System.out.print(root.val+" ");
     do_inorder(root.right);
     return;
   }
      public void postorder()
   {
     do_postorder(root);
     System.out.print("\n");
   }
   private void do_postorder(Node<T> root)
   {
     if(root==null){
     return;
     }
     do_postorder(root.left);
     do_postorder(root.right);
     System.out.print(root.val+" ");
     return;
   }
   public int height()
   {
      return find_height(root);
   }
   private int find_height(Node<T> root)
   {
     
     return root==null ? -1 :Math.max(find_height(root.left),find_height(root.right))+1;
   }
   public int depth(T val)
   {
     return find_depth(root,val,0);
   }
   private int find_depth(Node<T> root,T val,int depth)
   {
      if(root==null)
      return 0;
      if(root.val.compareTo(val)==0)
      {
      return depth;
      }
      if(root.val.compareTo(val)>0)
      {
        return find_depth(root.left,val,depth+1);
      }
      else
      {
       return find_depth(root.right,val,depth+1);
      }
   }
   public void levelorder()
   {
      do_levelorder(root);
   }
   private void do_levelorder(Node<T> root)
   {
     Queue<Node<T>> q=new LinkedList<>();
     q.add(root);
     while(!q.isEmpty())
     {
       int n=q.size();
       while(n-->0)
       {
         Node<T> node=q.poll();
         System.out.print(node.val+" ");
          if(node.left!=null) q.add(node.left);
         if(node.right!=null) q.add(node.right);
       }
       System.out.println();
     }
     System.out.println();
   }
   public boolean is_FBT(){
    return check_FBT(root);
 }
 private boolean check_FBT(Node<T> root)
 {
   if(root==null)
   {
    return true;
   }
   if((root.left!=null && root.right==null) || (root.left==null && root.right!=null))
   {
     return false;
   }
    return check_FBT(root.left) && check_FBT(root.right);
 }
 public boolean is_CBT(){
  return check_CBT(root);
}
private boolean check_CBT(Node<T> root)
{
 Queue<Node<T>> q=new LinkedList<>();
 q.add(root);
 boolean flag=false;
 while(!q.isEmpty())
 {
     Node<T> node=q.poll();
     if (node == null) {
        flag = true;
    } else {
        if (flag) {
            return false; 
        }
      }
      if(!flag){
      q.add(node.left);
      q.add(node.right);
      }
 }
 return true;
}
}
public class Bst {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
          BST<Integer> tree=new BST<>();
         tree.insert(7);
    }
}
