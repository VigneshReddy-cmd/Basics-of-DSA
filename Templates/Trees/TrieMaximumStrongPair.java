package Trees;

import java.util.Arrays;

class Node{
    Node[] child=new Node[2];
    int count[]=new int[2];
}
class Trie{
    private Node root;
    public Trie(){
        root=new Node();
    }
    public void insert(int num){
        Node temp=root;
       for(int i=20;i>=0;i--){
        // for storing the 20 bit number from msb to the lsb
        // storage in trie is msb------lsb top-down
        int idx=( num >> i) &1;
         if(temp.child[idx]==null){
            // for new existing number
            Node newnode=new Node();
            temp.child[idx]=newnode;
         }
          // make count of indx++;
          temp.count[idx]++;
          // for nextone
          temp=temp.child[idx];
       }
    }
    
    public void delete(int num){
      // same as traversal but decrease count
      //no need of checkingnull because using sliding window
      Node temp=root;
      for(int i=20;i>=0 ; i--)
      {
         int idx= (num >> i) & 1;
         temp.count[idx]--;
         temp = temp.child[idx];
      }
    }

    // imp to find the max xor from he exixting 
    // by choosing the appriotate index to get the maxxor then we need to choose
    // the bits which make the current bits are 1
    // if 1=ids then 0
    // if 0=idx then choose 1 
    // choosing is dobne by checking if avaliable if not there choose the exixting one
    public int max_Xor(int num){
        Node temp=root;
        // base xor
        int xor = 0;
        int curr_xor = 1<<20 ;
        for(int i=20;i>=0;i--)
        {
             int idx= (num >> i) & 1;

             int required_bit= 1-idx;

             if(temp.count[required_bit]>0)
             {
                 // choose
                 // increment the bits and make num
                 xor = xor | curr_xor;
                 temp=temp.child[required_bit];
             }
             else{
                // else stays as zero for this bit no change or add
                 temp=temp.child[idx];
             }
              curr_xor>>=1;
        }
        return xor;
    }
}
class Solution {
    public int maximumStrongPairXor(int[] nums) {
    //   create Trie node[2],int count[2];
    // insert delete max_xor
    // sort
    Arrays.sort(nums);

    // now apply sliding window x<= 2*y
   int l=0;
   int r=0;
   int n=nums.length;
   int maxXor=0;
   Trie trie=new Trie();
   while(r<n){
     // insert
     trie.insert(nums[r]);

     // window check
    //   for new one
    // nums[left]=x
    // nums[right]=y;
     while( 2 * nums[l] < nums[r])
     {
         trie.delete(nums[l++]);
     }
     maxXor=Math.max(maxXor,trie.max_Xor(nums[r++]));
   }
   return maxXor;
    }
}