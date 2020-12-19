package barrier.array;

import barrier.Barrier;

public class ArrayBarrier implements Barrier {
    private int[] locks;

    public ArrayBarrier(int n) {
        locks = new int[n];
    }

    @Override
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
