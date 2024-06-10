import java.util.HashMap;
public class iternary {
    public static String findstart(HashMap<String,String> map)
    {
        HashMap<String,String> revmap=new HashMap<>();
        for(String key:map.keySet())
        {
            revmap.put(map.get(key), key);
        }
        for(String keys:map.keySet())
        {
            if(revmap.containsKey(keys))
            {
                continue;
            }
            else{
                return keys;
            }
        }
        return null;
    }
   public static void main(String nani[])
   {
    HashMap<String,String> map=new HashMap<>();
    map.put("D","E");
    map.put("B","C");
    map.put("C","D");
    map.put("A","B");
    String start;
    start= findstart(map);
    // start=map.get(start);
//    System.out.print(start);

   // print
//   for(String key:map.keySet())
//   {
//      System.out.print(map.get(key));
//   }
while(map.containsKey(start))
{
    
    System.out.print(start+"->");
    start=map.get(start);
}
System.out.print(start);
   } 

}
