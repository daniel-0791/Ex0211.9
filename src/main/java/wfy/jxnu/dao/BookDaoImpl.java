package wfy.jxnu.dao;

import wfy.jxnu.mo.Book;
import wfy.jxnu.mo.OrderItem;
import wfy.jxnu.util.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements  BookDao{

    public List<Book> getRecommThisWeek(int size) throws SQLException{
        List<Book> bookList=new ArrayList<Book>();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        try{

            connection=JdbcUtils.getConn();//get connection;
            String sql="select  book_name,book_Image,book_Introduction from bookt\n" +
                    "order by book_SaleNum desc \n" +
                    "limit 0,?";
            preparedStatement=connection.prepareStatement(sql);

            preparedStatement.setInt(1,size);
            resultSet=preparedStatement.executeQuery();  //结果集合

            while (resultSet.next()){

                Book  book=new Book();
                book.setBookName(resultSet.getString(1));
                book.setBookImage(resultSet.getString(2));
                book.setBookIntroduction(resultSet.getString(3));


                bookList.add(book);


            }




            return bookList;

        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);

        }




    }

    @Override
    public List<Book> getRecommadation(int start, int size) throws SQLException {
        List<Book> bookList=new ArrayList<Book>();

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        try {

            connection = JdbcUtils.getConn();
            String sql = "select book_id,book_name,book_Author,book_image," +
                    "price,book_ISBN,book_Introduction,bookType_Name," +
                    "publishing_Name  " +
                    "from bookinfoview limit ?,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,start);
            preparedStatement.setInt(2, size);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt(1));
                book.setBookName(resultSet.getString(2));
                book.setBookAuthor(resultSet.getString(3));
                book.setBookImage(resultSet.getString(4));
                book.setPrice(resultSet.getFloat(5));
                book.setBookISBN(resultSet.getString(6));
                book.setBookIntroduction(resultSet.getString(7));
                book.setBookTypeName(resultSet.getString(8));
                book.setPublishingName(resultSet.getString(9));

                bookList.add(book);
            }

            return bookList;
        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }

    @Override
    public int getBookNumber() throws SQLException {
        int bookNumber=0;
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try{
            connection=JdbcUtils.getConn();
            statement=connection.createStatement();
            String sql="select count(*) booknumber  from bookinfoview";
            resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                bookNumber= resultSet.getInt(1);
            }

            return bookNumber;
        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConn(connection);
        }



    }

    @Override
    public Book findById(int id) throws SQLException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Book book = new Book();
        try {

            connection = JdbcUtils.getConn();
            String sql = "select book_id,book_name,book_Author,book_image," +
                    "price,book_ISBN,book_Introduction,bookType_Name," +
                    "publishing_Name,book_Num-book_SaleNum    " +
                    "from bookinfoview where book_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);


            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                book.setBookId(resultSet.getInt(1));
                book.setBookName(resultSet.getString(2));
                book.setBookAuthor(resultSet.getString(3));
                book.setBookImage(resultSet.getString(4));
                book.setPrice(resultSet.getFloat(5));
                book.setBookISBN(resultSet.getString(6));
                book.setBookIntroduction(resultSet.getString(7));
                book.setBookTypeName(resultSet.getString(8));
                book.setPublishingName(resultSet.getString(9));
                book.setBookNum(resultSet.getInt(10));


            }

            return book;
        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }
    }


    @Override
    public boolean updateBookNum(List<OrderItem> items, Connection connection) throws SQLException {
        boolean flag = false;

        PreparedStatement preparedStatement = null;

        try {

            String sql = "update bookt\n" +
                    "set book_SaleNum =book_SaleNum+?\n" +
                    "where book_id=?";
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < items.size(); i++) {
                OrderItem item = items.get(i);
                preparedStatement.setInt(1, item.getBuyNum());
                preparedStatement.setInt(2, item.getBookId());

                // 3) 将一组参数添加到此 PreparedStatement 对象的批处理命令中。
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            flag = true;
            return flag;

        } finally {
            JdbcUtils.closeStatement(preparedStatement);

        }
    }

    @Override
    /**
     * 使用存储过程
     */
    public int getBookNumber(String bookTypeName) throws SQLException {
        return 0;
    }


    public List<Book> getRecommadation(int size) throws SQLException {
        List<Book> bookList=new ArrayList<Book>();

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        try {

            connection = JdbcUtils.getConn();
            String sql = "SELECT book_Name,book_image\n" +
                    "FROM bookt limit 0,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, size);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setBookName(resultSet.getString(1));
                book.setBookImage(resultSet.getString(2));

                bookList.add(book);
            }

            return bookList;
        }finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeConn(connection);
        }

    }
}
