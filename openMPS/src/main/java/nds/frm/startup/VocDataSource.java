package nds.frm.startup;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;



public class VocDataSource {
    private static DataSource      loadDs;

	@Autowired
	public void setDataSource (DataSource dataSource) {
        loadDs = dataSource;
	}

	public static DataSource getDataSource(){
		return loadDs;
	}
}
