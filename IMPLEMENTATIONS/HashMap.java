import java.util.*;

public class Hashing2 {
    static class HashMap<K, V> {
        // angular brackets defines pairs / parameters types k /v may be any data type
        // or generics
        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int n; // number of the nodes present total
        private int N; // number of the Buckets present or Array size
        private LinkedList<Node> buckets[]; // array o ftype linked list;
        // default constructor hashmap

        @SuppressWarnings("unchecked")
        public HashMap() {
            this.N = 4; // specifing belongs
            this.buckets = new LinkedList[4]; // array of linkedlists initializes
            for (int i = 0; i < N; i++) {
                this.buckets[i] = new LinkedList<>(); // creating an empty linked list at every index of an array
            }
        }

        @SuppressWarnings("unchecked")
        private void rehash() {
            
            // duplicating current bucket
            // increase size and call put method for each node in duplicate buckets
            LinkedList<Node> Dup_buckets[] =new LinkedList[N];
            for (int i = 0; i < N; i++) {
                Dup_buckets[i] = new LinkedList<>();}
            for(int i=0;i<N;i++)
            {
                Dup_buckets[i]=buckets[i];
            }
            N*=2;
            buckets = new LinkedList[N];
            // creating ll at new buckets
            for (int i = 0; i < N ; i++) {
                buckets[i] = new LinkedList<>();
            }

            for (int i = 0; i < Dup_buckets.length; i++) {
                LinkedList<Node> ll = Dup_buckets[i]; // get linkesd list
                for (int j = 0; j < ll.size(); j++) {
                    // traversal array=>ll=>node=ll.get(index)=>node.value,node.key get()or add()
                    Node node = ll.get(j);
                    put(node.key, node.value);
                }
            }

        }

        private int hashFunction(K key) {
            int bi = key.hashCode(); // it will retuen 2^-31 to 2^31so we want with in range use mod function with
                                     // array size 'N' value and use abs for _ve values
            return Math.abs(bi) % N;
        }

        private int search_LL(K key, int bi) {
            LinkedList<Node> ll = buckets[bi];
            for (int i = 0; i < ll.size(); i++) {
                if (ll.get(i).key == key) // get method for searching
                    return i;
            }
            return -1; // not avaliable
        }

        public void put(K key, V value) {
            // exixts update else new
            // get bucket index to check if exixts
            // if exixt then choose the dataindex i.e at which no.of node the linked list
            // exist
            int bi = hashFunction(key);
            int di = search_LL(key, bi); // if dataindex is valid then exists else not;
            if (di == -1) {// key not exixts new node
                buckets[bi].add(new Node(key, value)); // array=>linkedlist add() new node
                n++; // adding nodes increases n value
            } else { // key exists update value of the given key
                Node node = buckets[bi].get(di); // traversal array=>linkedlist=>node get() and update
                node.value = value;
            }
            double lambda = (double) n / N;
            if (lambda > 2.0) {
                rehash();
            }

        }

        public ArrayList<K> KeySet() {
            ArrayList<K> keys = new ArrayList<>();
            for (int bi = 0; bi < buckets.length; bi++) {
                LinkedList<Node> ll = buckets[bi];
                for (int di = 0; di < ll.size(); di++) {
                    Node node = ll.get(di);
                    keys.add(node.key);
                }
            }
            return keys;
        }

        public boolean containsKey(K key) {
            int bi = hashFunction(key);
            int di = search_LL(key, bi);
            if (di == -1) {
                return false;
            } else {

                return true;
            }
        }

        public V get(K key) {
            int bi = hashFunction(key);
            int di = search_LL(key, bi);
            if (di == -1) {
                return null;
            } else {
                Node node = buckets[bi].get(di);
                return node.value;
            }
        }

        public V remove(K key) {
            int bi = hashFunction(key);
            int di = search_LL(key, bi);
            if (di == -1) { // search exixts array=> ll=> node.remove() return;
                return null;
            } else {
                Node node = buckets[bi].remove(di);
                n--;
                return node.value;

            }
        }
        public void getall_keys()
        {
            for(int i=0;i<N;i++)
            {
                for(int j=0;j<buckets[i].size();j++)
                {
                    Node node=buckets[i].get(j);
                    System.out.println(node.key+" "+node.value);
                }
            }
        }
       public int size()
       {
        return buckets.length;
       }
        public boolean is_empty() {
            return n == 0;
        }
    }// hash map ends here&&&&&&&&&**********************************

    public static void main(String nani[]) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("NANI", 88);
        map.put("VIGNESH", 77);
        map.put("BUBBY", 88);
        
        ArrayList<String> Keys = map.KeySet();
        for (int i = 0; i < Keys.size(); i++) {
            System.out.println(Keys.get(i) + " VALUE IS" + map.get(Keys.get(i)));
        }
        System.out.println(map.get("NANI"));
        System.out.println("NANI_REMOVED" + map.remove("NANI"));
        System.out.println(map.get("NANI"));
        System.out.print(map.size());
        map.put("BUBBwY", 88);
        map.put("BUBBYds", 88);
        map.put("BUBBYd", 88);
        map.put("BUBBYda", 88);
        map.put("BUBBYsd", 88);
        map.put("BUBBYq", 88);
        map.put("BUBBwY", 88);
        map.put("BUBBwdY", 88);
          System.out.println(map.size());
         map.getall_keys();
         // printing using the instance 
         System.out.println(map);          // out :Hashing2$HashMap@87aac27   why?
    }
}
