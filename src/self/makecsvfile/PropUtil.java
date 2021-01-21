package self.makecsvfile;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class PropUtil {

	public static PropInfo getProperties(String filename) {
		Properties properties = new Properties();

		try {
			InputStream istream = new FileInputStream(filename);
			properties.load(istream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Mapに格納
		PropInfo pinfo = new PropInfo();

		for (Map.Entry<Object, Object> e : properties.entrySet()) {
			if ("filename".equals(e.getKey())) {
				pinfo.prefix = (String)e.getValue();
				continue;
			}
			if ("columns".equals(e.getKey())) {
				pinfo.columns = ((String)e.getValue()).split(",");
				continue;
			}

			if ("newline".equals(e.getKey())) {
				pinfo.newline = (String)e.getValue();
				continue;
			}

			PropInfo.ColumnData data = pinfo.new ColumnData();
			String[] tmp = ((String)e.getValue()).split(",");
			data.column = (String)e.getKey();
			data.dataType = tmp[0];
			data.execType = tmp[1];
			data.data = new String[tmp.length-2];
			for (int i=2; i<tmp.length; i++) {
				data.data[i-2] = tmp[i];
			}
			pinfo.columnData.put(data.column, data);
		}

		return pinfo;
	}

}
