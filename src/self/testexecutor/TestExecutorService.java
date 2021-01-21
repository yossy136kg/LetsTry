package self.testexecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestExecutorService {

	public static void main(String[] args) {
		// パラメータの入力チェック

		// パラメータ：終了時刻
		String paramEndTime = "01:09";

		// プロパティ値の入力チェック

		// 業務処理タイムアウト時間（分）
		String propTimeoutMM = "1";

		// 業務処理の処理間隔（ミリ秒）
		String propExecTerm = "10000";

		String nowDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
		LocalDateTime endDateTime = LocalDateTime.parse(nowDate + "T" + paramEndTime,
				DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		ExecutorService executor = Executors.newSingleThreadExecutor();

		while (true) {

			// 終了判定
			// 同時刻もしくは、終了時間を過ぎている場合は処理を終了する
			if (LocalDateTime.now().isEqual(endDateTime) || LocalDateTime.now().isAfter(endDateTime)) {
				// ループ処理を終了する
				break;
			}

			try {

				// インデックスファイルリスト取得

				{
					// インデックスファイルリスト>0

					// DBコネクション取得

					// 業務処理スレッドを実行
					Future<Long> future = executor.submit(new TestCallable());

					// 指定した時間待機する
					Long ret = future.get(Integer.valueOf(propTimeoutMM), TimeUnit.MINUTES);

					// 戻り値が正常の場合
					// DBコミット

					System.out.println("業務処理の戻り値 : " + ret);

				}

				// 業務処理の実行間隔をあける
				Thread.sleep(Long.valueOf(propExecTerm));

			} catch (InterruptedException e) {
				// DBロールバック
				e.printStackTrace();
			} catch (ExecutionException e) {
				// DBロールバック
				e.printStackTrace();
			} catch (TimeoutException e) {
				// 業務処理タイムアウト

				// DBロールバック
				e.printStackTrace();
			} finally {
				// DBクローズ
			}
		}

		System.out.println(LocalDateTime.now());

		// スレッドに中断指示
		executor.shutdown();
		try {
			// 中断指示から終了されるのを待つ
			if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
				// 全てのスレッドを中断
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			// 全てのスレッドを中断
			executor.shutdownNow();
		}

		System.exit(0);
	}

}
