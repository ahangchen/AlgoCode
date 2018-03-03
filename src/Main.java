import static java.lang.Integer.min;

public class Main {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        if (prices.length == 1) return prices[0];
        int curProfit = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                curProfit += prices[i + 1] - prices[i];
            }
        }
        return curProfit + prices[0];
    }

    public int reverse(int x) {
        int reverse = 0;
        while (x / 10 != 0) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        reverse = reverse * 10 + x % 10;

        return reverse;
    }

    public int maxArea(int[] height) {
        int s, maxArea = 0;
        int l = 0, r = height.length - 1;
        while (l < r) {
            s = (r - l) * min(height[l], height[r]);
            if (s > maxArea)
                maxArea = s;
            if (height[l] > height[r])
                r--;
            else
                l++;
        }
        return maxArea;
    }
     public String intToRoman(int num) {
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++) {
                while(num - values[i] > 0)
                    num -= values[i];
                    roman.append(strs[i]);
        }
        return roman.toString();
    }
    public void rotate(int[][] matrix) {
        if(matrix == null) return;
        int n = matrix.length;
        int[][] matrixTemp = new int[n][n];
        for(int i = 0; i < n; i++) {
            for (int j= 0;j<n;j++) {
                matrixTemp[i][j] = matrix[n - j - 1][i];
            }
        }
        matrix = matrixTemp;
    }
     public int maxSubArray(int[] A) {
//        int curMax = 0;
//        for(int i = 0; i < A.length; i ++) {
//            int sum = A[i];
//            for(int j = i + 1;j < A.length; j++) {
//                sum += A[j];
//                curMax = curMax > sum ? curMax : sum;
//            }
//        }
//        return curMax;
         int i = 0;
         int curSum = A[0], curBad = 0;
         while(i < A.length) {
             if(A[i] > curBad) {
                 curSum += A[i];
             } else {
                 curBad += A[i];
             }
             i++;
         }
    }

    public static void main(String[] args) {
        // write your code here
        int[] A = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.print(new Main().maxSubArray(A));
    }
}
