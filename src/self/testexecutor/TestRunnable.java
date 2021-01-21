package self.testexecutor;
public class TestRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getId());
	}

}
