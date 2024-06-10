import java.util.LinkedList;
import java.util.Queue;

class B_TREE {
    static class Node { // data type of one node left and right side is a node and middle is data
       private int data;
        private Node left;
       private  Node right;

         Node(int data) // public
        {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private static class BinaryTree {
        static int index = -1;

        Node buildTree(int[] nodes) {
            index++;
            if (nodes[index] == -1) {
                return null;
            }
            Node newnode = new Node(nodes[index]);
            newnode.left = buildTree(nodes);
            newnode.right = buildTree(nodes);

            return newnode;
        }
    }

    public static void preorder(Node root) {
        if (root == null) {
            System.out.print(" End node - ");
            return;
        }
        System.out.print(root.data + " - ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(Node root) {
        if (root == null) {
            System.out.print(" Endnode -");
            return;
        }
        inorder(root.left);
        System.out.print(root.data);
        inorder(root.right);
    }

    public static void postorder(Node root) {
        if (root == null) {
            System.out.print(" Endnode -");
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data);

    }

    public static void levelorder(Node root) {
        // create queue
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            Node newnode = q.remove();
            if (newnode == null) {
                System.out.print("\n"); // null nextline
                if (q.isEmpty())
                    break;
                else
                    q.add(null);
            } else {
                System.out.print(newnode.data + " ");
                if (newnode.left != null) {
                    q.add(newnode.left);
                }
                if (newnode.right != null) {
                    q.add(newnode.right);
                }
            }
        }
    }

    public static int count_nodes(Node root) {
        if (root == null)
            return 0;
        int count_left = count_nodes(root.left);
        int count_right = count_nodes(root.right);
        return count_left + count_right + 1;
    }

    public static int sum_nodes(Node root) {
        if (root == null) {
            return 0;
        }
        int data = root.data;
        int sum_left = sum_nodes(root.left);
        int sum_right = sum_nodes(root.right);
        return sum_left + sum_right + data;
    }

    public static int find_height(Node root) {
        if (root == null)
            return 0;
        int left_height = find_height(root.left);
        int right_height = find_height(root.right);
        int my_height = Math.max(left_height, right_height) + 1;
        return my_height;
    }

    public static int find_diameter(Node root) {
        if (root == null)
            return 0;
        int dia_left = find_diameter(root.left);
        int dia_right = find_diameter(root.right);
        int dia_through_root = find_height(root.left) + find_height(root.right) + 1;
        return Math.max(dia_through_root, Math.max(dia_left, dia_right));
    }

    // Tpo reduce time complexity of the diamenter is O(n^2) to O(n) maintain height
    // and dia of nide in one datastructure
    // above dia f(x) uses O(n^2) because of everu node is visited 2 times i.e for
    // hieght and dia
    // now we visit once and store the dia and height
    private static class Treeinfo {
        int height;
        int dia;

        Treeinfo(int height, int dia) {
            this.height = height;
            this.dia = dia;
        }
    } // use this datatype to acess the datatype

    public static Treeinfo find_diameter_n(Node root) {
        // returns node of height and the diameter.
        // createtreeinfos for every node call recursively from rightto left and find
        // dia and height pass
        // base case return null
        if (root == null)
            return new Treeinfo(0, 0);
        Treeinfo left = find_diameter_n(root.left);
        Treeinfo right = find_diameter_n(root.right);
        // height
        int myheight = Math.max(left.height, right.height) + 1;
        // dia
        int dia1 = left.dia;
        int dia2 = right.dia;
        int dia_root = left.height + right.height + 1;
        int my_dia = Math.max(dia_root, Math.max(dia1, dia2));

        return new Treeinfo(myheight, my_dia); // returning new object or datastructure

    }
    public static void levelorder_sum(Node root,int k)
    {
        if(root==null)
        {
            System.out.println("null");
        }
        int sum=0;
        int check_node_level=0;
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        // q.add(null);  // we dont add null here
        while(!q.isEmpty())
        {
           //NOTE: when we reach a level we have all the elements of that level present in that quque
               int size=q.size(); 
           while(size>0)
           {
            Node newnode=q.peek();
            q.remove();
            // we got the first element in the queue
            // we have to manipulate here
            if(check_node_level==k)
            {
                sum+=newnode.data;
            }
            
            else
            {
                if(newnode.left!=null)
              q.add(newnode.left);  
              if(newnode.right!=null)
              q.add(newnode.right);    
            }
        }
        if(check_node_level==k)
        break;
       check_node_level++;
        }
        System.out.println(sum);

    }

    public static void main(String nani[]) {
        int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        System.out.println(root.data + " root is\n");
        preorder(root);
        ;// static element root to children left side
        System.out.print("\n\n");
        inorder(root); // children root children
        System.out.print("\n\n");
        postorder(root); //
        System.out.print("\n\n");
        levelorder(root);// level wise printed
        System.out.println("NUMBER OF NODES :" + count_nodes(root));
        System.out.println("Sum of nodes:" + sum_nodes(root));
        System.out.println("height of tree :" + find_height(root));
        System.out.println("Diameter of tree using O(n^2):" + find_diameter(root));
        System.out.println("Diameter of tree using O(n):" + find_diameter_n(root).dia + "    Height in dia :"
                + find_diameter_n(root).height);
        // each level sum first we find levels wise sum 
        levelorder_sum(root, 1);
    }
}