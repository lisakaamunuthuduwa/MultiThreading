//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MyThread1 thread1=new MyThread1();
        Thread thread2=new Thread(new MyRunnable2());

        thread1.start();
        thread2.start();

    }
}

class MyThread1 extends Thread {
    public void run(){
        for(int i=0; i<=10; i++){
            System.out.println("Thread (extended):" +i);
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class MyRunnable2 implements Runnable{
    public void run(){
        for(int i=0;i<=10; i++){
            System.out.println("Thread (implemented):"+i);
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}