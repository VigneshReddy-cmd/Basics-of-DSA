public class Kmp {
    private static void cal_lps(int[] lps, int m, String pattern) {
        int len = 0;
        lps[0] = 0; // lps[0] is always 0
        int i = 1;
        
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    } 
    
    protected static boolean string_match(String str, String pattern) {
        int n = str.length();
        int m = pattern.length();
        int lps[] = new int[m];
        cal_lps(lps, m, pattern);
        
        int i = 0; // index for str
        int j = 0; // index for pattern
        
        while (i < n) {
            if (pattern.charAt(j) == str.charAt(i)) {
                i++;
                j++;
            }
            
            if (j == m) {
                return true; // found pattern at index i - j
                // j = lps[j - 1]; // If you want to find multiple matches, uncomment this
            } else if (i < n && pattern.charAt(j) != str.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        String str = "ABC ABCDAB ABCDABCDABDE";
        String pattern = "ABCDABD";
        System.out.print(string_match(str, pattern));
    }
}
