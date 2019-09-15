import java.util.Random;

public class Main {
    public static void main(String [] args){
        Object[]arr = new Object[15];
        Random r = new Random();
        for (int i =0; i<arr.length;i++){
            arr[i]=r.nextInt(50);
        }
        QuickSort q= new QuickSort(arr);
        q.sort(3);
    }
}
