package wfy.jxnu.dao;

import wfy.jxnu.mo.BookType;
import wfy.jxnu.util.JdbcUtils;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookTypeDaoImpl implements BookTypeDao{


    @Override
    public List<BookType> countSaledNumbyType()  throws  Exception{
        List<BookType> bookTypeList=new ArrayList<BookType>();
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try {

            connection = JdbcUtils.getConn();
            String sql = "SELECT booktype_name,booktypesalednum\n" +
                    "FROM bookTypeSaledView";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                BookType bookType = new BookType();
                bookType.setBookTypeName(resultSet.getString(1));
                bookType.setBookTypeSaledNum(resultSet.getInt(2));

                bookTypeList.add(bookType);
            }

            return bookTypeList;
        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConn(connection);
        }
    }
}
