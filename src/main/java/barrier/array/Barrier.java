package barrier.array;

import java.util.ArrayList;

public class Barrier {
    private int[] locks;
    public Barrier(int n) {
        locks = new int[n];
    }

    public void myWait(int index) {
        if (index == 0) {
            locks[0] = 1;
            while (locks[1] != 2) {

            }
        }
        if (index > 0 && index < locks.length - 1) {
            while (locks[index - 1] != 1) {

            }
            locks[index] = 1;
            while (locks[index + 1] != 2) {

            }
            locks[index] = 2;
        }

        if (index == locks.length - 1) {
            while (locks[index - 1] != 1) {

            }
            locks[index] = 2;
        }
    }
}
