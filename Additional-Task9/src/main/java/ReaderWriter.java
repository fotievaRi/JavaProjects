import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;


public class ReaderWriter {
    private Map<String, Integer> hm = new HashMap<String, Integer>();
    static Object o;
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String, Integer> item : hm.entrySet()){
            result.append(item.getKey());
            result.append(" --> ");
            result.append(item.getValue());
            result.append("\n");
        }
        return result.toString();
    }
    ReaderWriter(String[]FileWays){
        o = new Object();
        Thread [] threads = new Thread[FileWays.length];
        for(int i=0; i<FileWays.length;i++){
            threads[i]= new MyThraed(FileWays[i]);
            threads[i].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
    private class MyThraed extends Thread{
        Scanner in;
         MyThraed (String FileName){
            try {
                in = new Scanner(new FileReader(FileName));
            }
            catch (IOException ex){
                System.out.println ("Error! " + ex.getMessage ());
            }
        }
        private void add(String word){
             synchronized (o) {
                 int value = 1;
                 if (hm.get(word) != null) {
                     value += hm.get(word);
                 }
                 hm.put(word, value);
             }
        }
        @Override
        public void run()
        {
            while(in.hasNext()) {
                String word = in.next();
                char c = word.charAt(word.length() - 1);
                if (c == '.' || c == ',' || c == '?' || c == '!' || c == ';'|| c == ':'|| c == '-' ){
                    word = word.substring(0, word.length() - 1);
                }
                if (word.length()>1) {
                    add(word);
                }
            }
        }
    }
}


