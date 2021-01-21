package self.makecsvfile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeCsvFileForTestData {

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("プロパティファイルを指定してください");
			System.exit(1);
		}
		String filename = args[0];

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println("MakeCsvFileForTestData Start:" + sdf.format(new Date()));

		ExportCsv.export(PropUtil.getProperties(filename));

		System.out.println("MakeCsvFileForTestData End  :" + sdf.format(new Date()));

		System.exit(0);
	}
}
