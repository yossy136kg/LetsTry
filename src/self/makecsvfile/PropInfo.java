package self.makecsvfile;
import java.util.HashMap;
import java.util.Map;

public class PropInfo {

	public String prefix = null;
	public String[] columns = null;
	public String newline = null;
	public Map<String, ColumnData> columnData = new HashMap<String,ColumnData>();

	public class ColumnData {
		public String column;
		public String dataType;
		public String execType;
		public String[] data;
	}
}
