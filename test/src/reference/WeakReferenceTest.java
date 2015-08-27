package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;


public class WeakReferenceTest {
	public static boolean isRun = true;
	
	public static void main(String[] args) throws InterruptedException {
		String abc = new String("abc");
		System.out.println(abc.getClass()+"@"+abc.hashCode());
		final ReferenceQueue referenceQueue = new ReferenceQueue<String>();
		new Thread(){
			@Override
			public void run() {
				while(isRun){
					Object o = referenceQueue.poll();
					if(o != null){
						try{
							java.lang.reflect.Field referent = Reference.class.getDeclaredField("referent");
							referent.setAccessible(true);
							Object result = referent.get(o);
							System.out.println("gc will collect:"+result.getClass()+"@"+result.hashCode());
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}.start();
		PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc, referenceQueue);
		abc = null;
		Thread.currentThread().sleep(3000);
		System.gc();
		Thread.currentThread().sleep(3000);
		isRun = false;
	}
}
