package self.testexecutor;
import java.util.concurrent.Callable;

public class TestCallable implements Callable<Long> {

	@Override
	public Long call() throws Exception {

		// 業務処理開始

//		Thread.sleep(5000);

		// インデックスファイルリストのループ

		// インデックスファイル、ZIPファイル移動

		// シリアルNo取得

		// ZIPファイル解凍

		// PDFファイルをイメージ管理に登録

		System.out.println("業務処理終了");

		// 業務処理終了
		return 0L;
	}

}
