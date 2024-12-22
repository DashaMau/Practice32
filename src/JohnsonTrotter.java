import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JohnsonTrotter {

    public static List<int[]> generatePermutations(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        List<int[]> permutations = new ArrayList<>();
        int[] p = new int[n];
        int[] dir = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = i + 1;
            dir[i] = -1;
        }

        permutations.add(Arrays.copyOf(p, n));

        int k = 0;
        while (k != -1) {
            int largest = 0;
            k = -1;
            for (int i = 0; i < n; i++) {
                if (isMobile(p, dir, i)) {
                    if(p[i] > largest){
                        largest = p[i];
                        k = i;
                    }

                }
            }

            if (k != -1) {

                int j = k + dir[k];

                int temp = p[k];
                p[k] = p[j];
                p[j] = temp;

                temp = dir[k];
                dir[k] = dir[j];
                dir[j] = temp;

                for (int i = 0; i < n; i++) {
                    if (p[i] > p[j]){
                        dir[i] *= -1;
                    }
                }


                permutations.add(Arrays.copyOf(p, n));
            }
        }
        return permutations;
    }



    private static boolean isMobile(int[] p, int[] dir, int i) {
        int n = p.length;

        if (i < 0 || i >= n) {
            return false;
        }

        if(i + dir[i] < 0 || i + dir[i] >= n){
            return false;
        }

        return p[i] > p[i + dir[i]];
    }

    public static void main(String[] args) {
        int n = 4; 

        List<int[]> permutations = generatePermutations(n);

        System.out.println("Перестановки для " + n + " чисел:");
        for (int[] permutation : permutations) {
            System.out.println(Arrays.toString(permutation));
        }

        System.out.println("\nВсего перестановок: " + permutations.size());

    }
}