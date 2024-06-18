import java.util.*;

 class Solution {
    private static ArrayList<Integer> seive_of_Eris(int limit)
    {
        boolean arr[]=new boolean[limit+1];
        Arrays.fill(arr,true);
        for(int p=2;p*p<=limit;p++)
        {
            if(arr[p])
            {
                for(int i=p*p;i<=limit;i+=p)
                {
                    arr[i]=false;
                }
            }
        }
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=2;i<=limit;i++)
        {
            if(arr[i])
                list.add(i);
        }
        return list;
    }
   private static void genprimes(long m,long n)
   {
       int limit=(int) Math.sqrt(n);
       ArrayList<Integer> primes=seive_of_Eris(limit);
       boolean[] is_prime=new boolean[(int) (n-m+1)];
       Arrays.fill(is_prime,true);
    // if we get all primes with in the sqrt then remove thwe multiples of them in the next list this is segmented seive
          for(int p:primes)
          {
              long lowerlimit= (m/p)*p; // because if  m=10 and p=3 then we find the primes from 10 so update lower limit
              if(lowerlimit<m)// 10/3 *3 =9 ;9<10 lower limit =9+3 12 which is first multiple of the 3 non prime
              {
                  lowerlimit+=p;
                  
              }
              for(long i=lowerlimit;i<=n;i+=p)
              {
                  if(i!=p)
                  {
                      // System.out.println((int)(i-m));
                      is_prime[(int) (i-m)]=false;
                  }
              }
              
          }
                     for(long i=Math.max(m,2);i<=n;i++)
              {
                  if(is_prime[(int)(i-m)])
                      System.out.println(i);
              }
       System.out.println();
   }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0)
        {
           long m=sc.nextLong();
            long n=sc.nextLong();
            genprimes(m,n);
        }
        sc.close();
    }
}

