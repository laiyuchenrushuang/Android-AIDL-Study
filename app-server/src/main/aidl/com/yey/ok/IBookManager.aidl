package com.yey.ok;
import com.yey.ok.Book;
interface IBookManager{
    List<Book> getBookList();
    void addBook(in Book book);
}
