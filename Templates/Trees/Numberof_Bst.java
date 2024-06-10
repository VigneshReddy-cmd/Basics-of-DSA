package Trees;

import java.util.*;
class Node{
    int val;
    Node left;
    Node right;
   public Node(int val)
    {
        this.val=val;
        this.left=null;
        this.right=null;
    }   
} 
class Tree{
    private Node root;
    public Tree(int val)
    {
        this.root=new Node(val);
    }
    public void insert(int p,int arr[])
    {
        insertNode(root,p,arr);
    }
    private void insertNode(Node root,int p,int arr[])
    {
        if(root==null) return;
        if(root.val==arr[p])
        {
            Node left=null;
            if(2*p<arr.length) left=new Node(arr[2*p]);
            Node right=null;
            if(2*p+1<arr.length) right=new Node(arr[2*p+1]);
            root.left=left;
            root.right=right;
            return;
        }
        insertNode(root.left,p,arr);
        insertNode(root.right,p,arr);
        return;
    }
    // inorder
    public boolean check_bst() {
    return is_BST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
}

private boolean is_BST(Node root, int min, int max) {
    if (root == null) {
        return true;
    }
    if (root.val <= min || root.val >= max) {
        return false;
    }
    return is_BST(root.left, min, root.val) && is_BST(root.right, root.val, max);
}
  public int count_bst()
  {
    HashSet<Integer> set=new HashSet<>();
    docount_bst(root,set);
    return set.size();
  }
  private void docount_bst(Node root,HashSet<Integer> set)
  {
     if(root==null)
     {
       return;
     }
     docount_bst(root.left,set);
     docount_bst(root.right,set);
    if(is_BST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)){
      set.add(root.val);
    }
  }
}
public class Numberof_Bst {
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int t=sc.nextInt();
      while(t-->0)
      {
         int n=sc.nextInt();
         int arr[]=new int[n+1];
          for(int i=1;i<n+1;i++)
          {
            arr[i]=sc.nextInt();
          }
         Tree tree=new Tree(arr[1]);
         for(int i=1;i<=n/2;i++){
              tree.insert(i,arr);
          }
         System.out.println(tree.count_bst());
      }
    }
}