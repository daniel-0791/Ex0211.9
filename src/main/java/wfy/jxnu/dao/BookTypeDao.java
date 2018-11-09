package wfy.jxnu.dao;

import wfy.jxnu.mo.BookType;

import java.util.List;

public interface BookTypeDao {

    public List<BookType> countSaledNumbyType() throws Exception;
}
