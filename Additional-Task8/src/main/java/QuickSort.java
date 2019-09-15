import java.util.Random;

public class QuickSort {
    Object[] data;
    QuickSort(Object[]MyData){
        data= MyData;
    }
    private class Qsort extends Thread {
        int low;
        int high;
        Qsort(int low, int high){
            this.low=low;
            this.high=high;
        }
        public void qsort(int l, int h){
            int i = l;
            int j = h;
            int x = data[l+(h-l)/2].hashCode();
            do {
                while (data[i].hashCode() < x) ++i;
                while (data[j].hashCode() > x) --j;
                if (i <= j) {
                    int temp = data[i].hashCode();
                    data[i] = data[j];
                    data[j] = temp;
                    i++;
                    j--;
                }
            } while (i <= j);
            if (l< j) {
                qsort(l,j);
            }
            if (i< h) {
                qsort(i,h);
            }
        }
        @Override
        public void run() {
            qsort(low,high);
        }
    }
    public void sort(int numThreads){
        try{
            if(numThreads<=0){
                throw new Exception("This number of threads is negativ");
            }
            if (numThreads==1){
                Thread t = new Qsort(0, data.length-1);
                t.start();
                t.join();
                return;
            }
            if (data.length/numThreads<2){
                numThreads=data.length/2;
            }
            Thread []threads = new Thread[numThreads];
            int numElements = data.length/numThreads;
            int remainder = data.length%numThreads;
            threads[0]= new Qsort(0, numElements-1+remainder);
            threads[0].start();
            int j= numElements+remainder;
            for(int i=1; i<numThreads; i++){
                threads[i]= new Qsort(j, j+numElements-1);
                threads[i].start();
                j+=numElements;
            }
            try {
                for (Thread thread : threads) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        Thread t = new Qsort(0, data.length-1);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
        System.out.println("Interrupted");
    }
    }
}