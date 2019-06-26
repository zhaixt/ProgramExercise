/**
 * @author: zhaixt
 * @date: 2019/6/25 17:16
 */
public class ThreadlocalTest2 implements Cloneable{
    public static void main(String[] args){
        ThreadlocalTest2 p=new ThreadlocalTest2();
        System.out.println("0:"+p);
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                ThreadLocal<ThreadlocalTest2> threadLocal = new ThreadLocal<>();
                System.out.println("1:"+threadLocal);
                threadLocal.set(p);
                System.out.println("2:"+threadLocal.get());
                threadLocal.remove();
                try {
                    threadLocal.set((ThreadlocalTest2) p.clone());
                    System.out.println("3:"+threadLocal.get());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                System.out.println("4:"+threadLocal);
            }});
        t.start();
    }
}
