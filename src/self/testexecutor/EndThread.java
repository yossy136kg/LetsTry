package self.testexecutor;
public class EndThread {

	public static void main(String[] args) {
		ThreadGroup group = Thread.currentThread().getThreadGroup().getParent();
		Thread[] threads = new Thread[group.activeCount()];
		group.enumerate(threads, true);
		for (Thread th : threads) {
			System.out.println(th.getName());
		}

//		TestRunnable service = Executors.newSingleThreadExecutor();
	}

}
