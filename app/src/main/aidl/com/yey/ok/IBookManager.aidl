package com.yey.ok;
import com.yey.ok.Book;
// IBookManager接口中只支持方法，不支持声明静态常量。
// 普通的interface接口中是可以声明静态常量的， AIDL中不一样。
interface IBookManager{
    List<Book> getBookList();
    // 参数中必须标记好方向
    // in：输入型参数
    // out：输出型参数
    // inout：输入输出型参数
    void addBook(in Book book);
}
