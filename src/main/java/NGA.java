public class NGA {
    private char[] dirList = {'E', 'S', 'W', 'N'}; //所有方向
    private int dir = 0;  // 当前方向
    private int[][] arr;
    private int i;
    private int j;
    private int num = 1;


    public void moveNext() {
        switch (dirList[dir]) {
            case 'E':
                j++;
                break;
            case 'S':
                i++;
                break;
            case 'W':
                j--;
                break;
            case 'N':
                i--;
                break;
        }
    }

    public void getBack() {
        switch (dirList[dir]) {
            case 'E':
                j--;
                break;
            case 'S':
                i--;
                break;
            case 'W':
                j++;
                break;
            case 'N':
                i++;
                break;
        }
    }

    void cal(int n) {
        arr = new int[n][n];
        int time = 0;
        while (time < 4) {
            // 不是0就填入
            if (arr[i][j] == 0) {
                arr[i][j] = num++;
                time = 0;
            }
            moveNext();
            // 如果越界 || 撞墙了，回退+转向
            if (i == n || j == n || i < 0 || j < 0 || arr[i][j] !=0) {
                getBack();
                dir = dir == 3 ? 0 : dir + 1;
                time++;
                moveNext();
            }
        }
    }

    void show(int n) {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                System.out.print(arr[x][y] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        NGA nga = new NGA();
        nga.cal(8);
        nga.show(8);
    }
}

