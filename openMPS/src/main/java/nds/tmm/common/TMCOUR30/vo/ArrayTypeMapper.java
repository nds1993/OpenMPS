package nds.tmm.common.TMCOUR30.vo;

import java.sql.Array;
import java.sql.SQLException;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class ArrayTypeMapper implements TypeHandlerCallback {

  public void setParameter(ParameterSetter setter, Object parameter) 
        throws SQLException {
    throw new UnsupportedOperationException("Not implemented");
  }

  public Object getResult(ResultGetter getter) throws SQLException {
    Array array = getter.getResultSet().getArray(getter.getColumnName());
    if (!getter.getResultSet().wasNull()) {
      return array.getArray();
    } else {
      return null;
    }
  }

  public Object valueOf(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }
}