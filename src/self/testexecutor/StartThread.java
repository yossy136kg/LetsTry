package self.testexecutor;

public class StartThread {

	public static void main(String[] args) {
		Thread th = new Thread() {
			public void run() {
				while(true) {
//					System.out.println(Thread.currentThread().getName());
				}
			};
		};
		th.setName("TEST");
		th.start();

	}

}
