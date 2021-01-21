package self.makecsvfile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ExportCsv {

	public static void export(PropInfo prop) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Date now = new Date();
		String suffix = sdf.format(now);

		String fileName = prop.prefix + "_" + suffix + ".csv";
		try (
				FileWriter f = new FileWriter(fileName, false);
				PrintWriter p = new PrintWriter(new BufferedWriter(f));) {

			// 内容をセットする
			long start = 0;
			long end = Long.MAX_VALUE;
			for (int idx=0; idx<=(end-start-1); idx++) {

				// Column
				for (int i = 0; i < prop.columns.length; i++) {
					StringBuffer buf = new StringBuffer();
					PropInfo.ColumnData column = prop.columnData.get(prop.columns[i]);
					if (column == null) {
						buf.append("NO DATA");
						continue;
					}
					if ("CHAR".equals(column.dataType)) {
						buf.append("\"");
					}

					// data
					if ("T".equals(column.execType)) {
						// データを作成する範囲
						start = Long.valueOf(column.data[0]);
						end = Long.valueOf(column.data[1]);
						buf.append(start + idx);
					} else if ("F".equals(column.execType)) {
						// 固定値
						if (column.data.length==0) {
							buf.append("");
						} else {
							buf.append(column.data[0]);
						}
					} else if ("R".equals(column.execType)) {
						// ランダム
						int dcnt = column.data.length - 1;
						Random rand = new Random();
						int num = rand.nextInt(dcnt);
						buf.append(column.data[num]);
					} else if ("D".equals(column.execType)) {
						// 現在日付
						SimpleDateFormat sdf2 = new SimpleDateFormat(column.data[0]);
						buf.append(sdf2.format(now));
					} else if ("DI".equals(column.execType)) {
						SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
						Date dt = sdf3.parse(column.data[0]);
						Calendar cal = Calendar.getInstance();
						cal.setTime(dt);
						cal.add(Calendar.DAY_OF_MONTH, idx);
						buf.append(sdf3.format(cal.getTime()));
					} else {
						// その他
						buf.append(column.data[0]);
					}

					if ("CHAR".equals(column.dataType)) {
						buf.append("\"");
					}
					if (i != (prop.columns.length - 1)) {
						buf.append(",");
					}
					p.print(buf.toString());
					buf = null;
				}

				if ("CRLF".equals(prop.newline)) {
					p.print("\r\n");
				} else {
					p.print("\n");
				}

				// ファイルに書き出し
				p.flush();
			}

			NumberFormat nfNum = NumberFormat.getNumberInstance();
			System.out.println("ファイル:" + fileName + " " + nfNum.format(end-start) + "件");

		} catch (IOException | ParseException ex) {
			ex.printStackTrace();
		}

	}
}
