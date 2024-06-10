import java.util.*;
public class tire {
    static class Node
    {
        Node[] children;
        boolean eow;
        Node()
        {
              children=new Node[26]; // a-z
              for(int i=0;i<26;i++)
              {
                children[i]=null;
              }
              eow=false;
        }
    }
   final static Node root=new Node();
     private static void insert(String word) {
        Node curr=root;
        for(int i=0;i<word.length();i++)
        {
            int idx=word.charAt(i)-'a';
            if(curr.children[idx]==null)
            {
                curr.children[idx]=new Node();
            }
            if(i==word.length()-1)
            {
               curr.children[idx].eow=true;
            }
            curr=curr.children[idx];
        }
    }
    private static boolean search(String word)
    {
        Node curr=root;
          for(int i=0;i<word.length();i++)
          {
            int idx=word.charAt(i)-'a';
             if(curr.children[idx]==null)
            return false;
            if(i==word.length()-1)
            {
                if(!curr.children[idx].eow)
                return false;
            }
         
            curr=curr.children[idx];
          }
          return true;
    }
  public static boolean wordbreak(String key)
    {
        if(key.length()==0)
        {
            return true;
        }

        // break the string in to 2 parts then search for part1 and else search for second part recursively
        // if first part is avaliable then only go for second part loop the posssiblities of substrings of all the units of the key
        // initial the  substring is from (0,i) i=i++;
        for(int i=1;i<=key.length();i++)  // acessing substring so i<=key.length()
        {
                String part1=key.substring(0,i);
                String part2=key.substring(i);
                if(search(part1) && wordbreak(part2))
                {
                    return true;
                }
        }
        return false;

    }
     private static boolean startswith(String string) {
        Node curr=root;
        for(int i=0;i<string.length();i++)
        {
            int idx=string.charAt(i)-'a';
            if(curr.children[idx]==null)
            return false;
            curr=curr.children[idx];
        }
        return true;
    }
    public static void main(String args[])
    {
        List<String> list=new ArrayList<>();
        list.add("the");
        list.add("a");
        list.add("there");
        list.add("therere");
        list.add("their");
        list.add("any");
        for(String word :list)
        {
            insert(word);
        }
        System.out.println(search("the"));
        // System.out.println(search("a"));
        // System.out.println(search("anyy"));
        // System.out.println(search("any"));
        System.out.println(wordbreak("theathethetherethetherethethere"));
        // search a prefix of word
        System.out.println(startswith(""));
        // create trie ds and start with function but nsearch not required
    }
   

   
}
