import java.util.*;

public class Maxsegmentree {
    static int tree[];

    public static void init(int n) {
        tree = new int[4 * n];
    }

    public static void buildst(int i, int si, int sj, int arr[]) {
        if (si == sj) {
            tree[i] = arr[si];
            return;
        }
        int mid = (si + sj) / 2;
        buildst(2 * i + 1, si, mid, arr);
        buildst(2 * i + 2, mid + 1, sj, arr);
        tree[i] = Math.max(tree[2 * i + 1], tree[2 * i + 2]);
    }

    public static int getmaxutil(int i, int si, int sj, int qi, int qj) {
        if (si > qj || sj < qi) {
            return Integer.MIN_VALUE;
        } else if (si >= qi && sj <= qj) {
            return tree[i];
        } else {
            int mid = (si + sj) / 2;
            int leftAns = getmaxutil(2 * i + 1, si, mid, qi, qj);
            int rightAns = getmaxutil(2 * i + 2, mid + 1, sj, qi, qj);
            return Math.max(leftAns, rightAns);
        }
    }

    public static int getmax(int arr[], int qi, int qj) {
        int n = arr.length;
        return getmaxutil(0, 0, n - 1, qi, qj);
    }

    public static void updateutil(int i, int si, int sj, int idx, int newval) {
        if (idx > sj || idx < si) {
            return;
        }
        if (si == sj) {
            tree[i] = newval;
        }
        if (si != sj) {
            tree[i] = Math.max(tree[i], newval);
            int mid = (si + sj) / 2;
            updateutil(2 * i + 1, si, mid, idx, newval);
            updateutil(2 * i + 2, mid + 1, sj, idx, newval);
        }
    }

    public static void update(int arr[], int idx, int newval) {
        arr[idx] = newval;
        int n = arr.length;
        updateutil(0, 0, n - 1, idx, newval);
    }

    public static void main(String args[]) {
        int arr[] = { 6, 8, -1, 2, 17, 1, 3, 2, 4 };
        int n = arr.length;
        init(n);
        buildst(0, 0, n - 1, arr);

        // for (int i = 0; i < tree.length; i++) {
        // System.out.print(tree[i] + " ");
        // }
        System.out.println(getmax(arr, 2, 5));
        update(arr, 2, 20);
        System.out.println(getmax(arr, 2, 5));
    }
}