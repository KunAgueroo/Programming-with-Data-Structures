package app;

public class RecursiveTest {
    
    public int count = 0;

    public void run() {
        helperRun(5);
    }

    public void helperRun(int num) {
        if (num != 0) {
            count++;
            
            helperRun(--num);
        }   
    }








    public static void main(String args[]) {
        RecursiveTest r = new RecursiveTest();
        r.run();
        System.out.println("Count : " + r.count);
    }

}
