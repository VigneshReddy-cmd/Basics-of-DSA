package Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Block{
    Block arr[]=new Block[26];
    boolean flag;
    boolean containsKey(char ch){
      return this.arr[ch-'a']!=null;
    }
   void put(char ch,Block node)
    {
         this.arr[ch-'a']=node;
    }
    Block get(char ch){
     return this.arr[ch-'a'];
    }
    void setEnd(){
        this.flag=true;
    }
}





class Trie {
   private Block  root;
    public Trie() {
        root=new Block();
    }
    
    public void insert(String word) {
        Block node=root;
        for(int i=0;i<word.length();i++)
        {
             if(!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i),new Block());
             }
             node=node.get(word.charAt(i));
        }
        node.setEnd();
    }
    private Block return_endNode(String word){
         Block node=root;
        for(int i=0;i<word.length();i++)
        {
             char ch=word.charAt(i);
             if(!node.containsKey(ch)){
                return null;
             }
             node=node.get(ch);
        }
        return node;
    }
    public void search(String s,PriorityQueue<String> pq)
    {
        Block start= return_endNode(s);
        if(start==null)
        {
              return;
        }
        make_search(start,s,pq);
    }
    private void make_search(Block node,String str,PriorityQueue<String> pq){
        if(node.flag==true)
         {
             pq.add(str);
         }
         for(int i=0;i<26;i++)
         {
            char ch=(char)('a'+i);
             if(node.containsKey(ch))
             {
                Block curr=node.get(ch);
                 make_search(curr,str+ch,pq);
             }
         }
         return;
    }
}
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
          Trie trie=new Trie();
          for(String s:products)
          {
             trie.insert(s);
          }
          List<List<String>> list=new ArrayList<>();
          for(int i=0;i<searchWord.length();i++)
          {
            String s=searchWord.substring(0,i+1);
             PriorityQueue<String> pq=new PriorityQueue<>();
             trie.search(s,pq);
             List<String> temp=new ArrayList<>();
             while (!pq.isEmpty() && temp.size() < 3) {
                temp.add(pq.poll());
            }
             list.add(temp);
          }
          return list;
    }
}
