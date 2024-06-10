import java.util.*;
public class Shortest_inbinarymaze{
    private boolean check(int i,int j,int n,int m)
    {
        if(i>=0 && i<n && j>=0 && j<m) return true;
        else
         return false;
    }
    int shortestPath(int[][] arr, int[] src, int[] dest) {
        // Your code here
        if(src[0]==dest[0] && src[1]==dest[1]) return 0;
        int delrow[]={1,0,-1,0};
        int delcol[]={0,1,-1,0};
        Queue<int[]> q=new LinkedList<>();
        int ar[]={0,src[0],src[1]};
        q.add(ar);
        int n=arr.length;
        int m=arr[0].length;
        int[][] dist=new int[n][m];
        while(!q.isEmpty())
        {
            int cell[]=q.peek();
            q.remove();
            int r=cell[1];
            int c=cell[2];
            for(int i=0;i<4;i++)
            {
                int drow=r+delrow[i];
                int dcol=c+delcol[i];
                if(check(drow,dcol,n,m) && arr[drow][dcol]==1)
                {
                    if(cell[0]+1 <dist[drow][dcol])
                    {
                        dist[drow][dcol]=cell[0]+1;
                        if(drow==dest[0] && dcol==dest[1]) return cell[0]+1;
                        // int temp[]={cell[0]+1,drow,dcol};
                    }
                }
            }
        }
        return -1;
    }
}
