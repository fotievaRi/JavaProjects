

public class Deadlock implements Runnable{
    private First first = new First();
    private Second second = new Second();

    public void run(){
        second.s(first);
    }
    Deadlock(){
        Thread.currentThread().setName("Main thread");
        Thread t = new Thread(this);
        t.setName("Not main thread");
        t.start();
        first.f(second);
    }
    public class First{
        public synchronized void f(Second second){
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Error");
            }
            System.out.println(Thread.currentThread().getName());
            second.s(this);
        }
    };

    public class Second {
        public synchronized  void s(First first){
            System.out.println(Thread.currentThread().getName());
            first.f(this);
        }
    };

    public static void main(String [] args){
        Deadlock myDeadlock = new Deadlock();
    }
}
