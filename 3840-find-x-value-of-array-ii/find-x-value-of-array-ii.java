class Solution {
    static class Node {
        int[] remain = new int[5]; // Stores frequency of each remainder modulo k
        int prod = 1;              // Product of elements modulo k
    }

    static class SegmentTree {
        private int n;
        private int k;
        private Node[] tree;

        public SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.tree = new Node[4 * n];
            for (int i = 0; i < 4 * n; i++) {
                tree[i] = new Node();
            }
            build(nums, 0, 0, n - 1);
        }

        private Node merge(Node left, Node right) {
            Node res = new Node();
            res.prod = (left.prod * right.prod) % k;
            
            // Subarrays fully inside left child
            for (int i = 0; i < k; i++) {
                res.remain[i] += left.remain[i];
            }
            
            // Subarrays extending into right child (prefix from right child * full product of left child)
            for (int i = 0; i < k; i++) {
                int newRem = (i * left.prod) % k;
                res.remain[newRem] += right.remain[i];
            }
            
            return res;
        }

        private void build(int[] nums, int cur, int left, int right) {
            if (left == right) {
                int val = nums[left] % k;
                tree[cur].remain[val] = 1;
                tree[cur].prod = val;
                return;
            }
            int mid = left + (right - left) / 2;
            build(nums, 2 * cur + 1, left, mid);
            build(nums, 2 * cur + 2, mid + 1, right);
            tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
        }

        public void update(int treeIndex, int lo, int hi, int i, int val) {
            if (lo == hi) {
                for (int j = 0; j < k; j++) {
                    tree[treeIndex].remain[j] = 0;
                }
                tree[treeIndex].remain[val] = 1;
                tree[treeIndex].prod = val;
                return;
            }
            int mid = lo + (hi - lo) / 2;
            if (i <= mid) {
                update(2 * treeIndex + 1, lo, mid, i, val);
            } else {
                update(2 * treeIndex + 2, mid + 1, hi, i, val);
            }
            tree[treeIndex] = merge(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
        }

        public Node query(int treeIndex, int lo, int hi, int i, int j) {
            if (i <= lo && hi <= j) {
                return tree[treeIndex];
            }
            int mid = lo + (hi - lo) / 2;
            if (j <= mid) {
                return query(2 * treeIndex + 1, lo, mid, i, j);
            }
            if (i > mid) {
                return query(2 * treeIndex + 2, mid + 1, hi, i, j);
            }
            
            Node leftRes = query(2 * treeIndex + 1, lo, mid, i, j);
            Node rightRes = query(2 * treeIndex + 2, mid + 1, hi, i, j);
            return merge(leftRes, rightRes);
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] %= k;
        }

        SegmentTree tree = new SegmentTree(nums, k);
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1] % k;
            int start = queries[i][2];
            int x = queries[i][3];

            // 1. Update element at `index` to `value`
            tree.update(0, 0, n - 1, index, value);

            // 2. Query prefix-suffixes on range [start, n - 1]
            Node node = tree.query(0, 0, n - 1, start, n - 1);

            // 3. Store result for target remainder x
            result[i] = node.remain[x];
        }

        return result;
    }
}