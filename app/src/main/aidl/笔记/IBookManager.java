package 笔记;

public interface IBookManager extends android.os.IInterface {
  /**
   * Default类,继承了IBookManager接口
   */
  public static class Default implements IBookManager {

    @Override
    public java.util.List<com.yey.ok.Book> getBookList() throws android.os.RemoteException {
      return null;
    }

    @Override
    public void addBook(com.yey.ok.Book book) throws android.os.RemoteException {
    }

    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }

  /**
   * Binder本地对象类型Stub.
   */
  public static abstract class Stub extends android.os.Binder implements IBookManager {
    // 该Binder本地对象的唯一标识符,一般用当前Binder类名称表示.
    private static final String DESCRIPTOR = "com.yey.ok.笔记.IBookManager";

    public Stub() {
      // mOwner = this
      // mDescriptor = DESCRIPTOR
      // 传入之后,就可以使用queryLocalInterface()返回相应的Binder对象了.
      // attachInterface()是使用queryLocalInterface()的前提.
      this.attachInterface(this, DESCRIPTOR);
    }

      // 用于返回Binder对象， 是返回Binder本地对象还是Binder代理对象视进程而定。
      public static IBookManager asInterface(android.os.IBinder obj) {
          if ((obj==null)) {
              return null;
          }
          // queryLocalInterface() 该方法上面讲过， 通过描述符来查找Binder本地对象。
          android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
          // 如果查找的Binder对象不为空，且是在同一个进程(iin instanceof 笔记.IBookManager)
          if (((iin!=null)&&(iin instanceof IBookManager))) {
              // 那么就将查到当前进程中的Binder本地对象直接返回就行了。
              return ((IBookManager)iin);
          }
          // 如果不是同一个进程中的(iin instanceof 笔记.IBookManager)
          // 那么就返回这个Binder本地对象对应的Binder代理对象。
          // obj 是BpBinder类型的Binder代理对象,内部包含有Binder引用对象的句柄值.通过该句柄值就可以找到Binder引用对象再找到Binder实体对象,最后找到Binder本地对象.
          return new Stub.Proxy(obj);
      }
      // 返回当前Binder本地对象。
      @Override
      public android.os.IBinder asBinder() {
          return this;
      }

    /**
     * 这个方法是运行在服务端的Binder线程池中，客户端发起跨进程请求，最终会交由此方法处理。
     * @param code 用来确认客户端请求的目标方法是什么。
     * @param data 用来存放目标方法中的参数
     * @param reply 用来存入目标方法执行完成的返回值
     * @param flags
     * @return false 代表客户端请求失败，true代表客户端请求成功。
     * @throws android.os.RemoteException
     */
    @Override
    public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
      String descriptor = DESCRIPTOR;
      switch (code) {
        case INTERFACE_TRANSACTION: {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_getBookList: {
          data.enforceInterface(descriptor);
          java.util.List<com.yey.ok.Book> _result = this.getBookList();
          reply.writeNoException();
          reply.writeTypedList(_result);
          return true;
        }
        case TRANSACTION_addBook: {
          data.enforceInterface(descriptor);
          com.yey.ok.Book _arg0;
          if ((0!=data.readInt())) {
            _arg0 = com.yey.ok.Book.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.addBook(_arg0);
          reply.writeNoException();
          return true;
        }
        default: {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }

      //Binder代理类
      private static class Proxy implements IBookManager {
          // mRemote 是BpBinder类型的Binder代理对象,内部包含有Binder引用对象的句柄值.通过该句柄值就可以找到Binder引用对象再找到Binder实体对象,最后找到Binder本地对象.
          private android.os.IBinder mRemote;
          Proxy(android.os.IBinder remote) {
              mRemote = remote;
          }

          // 返回Binder代理对象
          @Override
          public android.os.IBinder asBinder() {
              return mRemote;
          }

          public String getInterfaceDescriptor() {
              return DESCRIPTOR;
          }

          /**
           * 该方法运行在客户端，当客户端调用该方法时，最终会调用服务端的onTransact().
           * @return
           * @throws android.os.RemoteException
           */
          @Override
          public java.util.List<com.yey.ok.Book> getBookList() throws android.os.RemoteException {
              android.os.Parcel _data = android.os.Parcel.obtain();
              android.os.Parcel _reply = android.os.Parcel.obtain();
              java.util.List<com.yey.ok.Book> _result;
              try {
                  _data.writeInterfaceToken(DESCRIPTOR);
                  // 开始RPC(远程过程调用)，最终调用的就是服务端的onTransact()。
                  // 执行该方法后，当前线程会被挂起，等待服务端进程onTransact()调用完成并返回返回值，然后唤醒客户端线程继续执行。
                  boolean _status = mRemote.transact(Stub.TRANSACTION_getBookList, _data, _reply, 0);
                  // 假如RPC失败，则通过IBookManager类型对象调用当前进程中getBookList()，
                  // getBookList()它是接口方法，需要当前进程自己该方法。
                  if (!_status && getDefaultImpl() != null) {
                      return getDefaultImpl().getBookList();
                  }
                  _reply.readException();
                  // RPC成功，从_reply中取出返回值并返回。
                  _result = _reply.createTypedArrayList(com.yey.ok.Book.CREATOR);
              }
              finally {
                  _reply.recycle();
                  _data.recycle();
              }
              return _result;
          }

          /**
           * 该方法运行在客户端，当客户端调用该方法时，最终会调用服务端的onTransact().
           * @param book
           * @throws android.os.RemoteException
           */
          @Override
          public void addBook(com.yey.ok.Book book) throws android.os.RemoteException {
              android.os.Parcel _data = android.os.Parcel.obtain();
              android.os.Parcel _reply = android.os.Parcel.obtain();
              try {
                  _data.writeInterfaceToken(DESCRIPTOR);
                  if ((book!=null)) {
                      _data.writeInt(1);
                      book.writeToParcel(_data, 0);
                  }
                  else {
                      _data.writeInt(0);
                  }
                  // RPC开始，当前线程会被挂起。
                  // RPC完成唤醒线程，继续下面代码执行。
                  boolean _status = mRemote.transact(Stub.TRANSACTION_addBook, _data, _reply, 0);
                  if (!_status && getDefaultImpl() != null) {
                      // 如果RPC失败，尝试调用当前进程中的方法，但是当前进程需要实现该方法。
                      getDefaultImpl().addBook(book);
                      // addBook() 不需要返回值， 所以return就行了。
                      return;
                  }
                  _reply.readException();
              }
              finally {
                  _reply.recycle();
                  _data.recycle();
              }
          }
          public static IBookManager sDefaultImpl;
      }

    static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);

    static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);

    public static IBookManager getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }

    public static boolean setDefaultImpl(IBookManager impl) {
      if (Stub.Proxy.sDefaultImpl == null && impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
  }
  public java.util.List<com.yey.ok.Book> getBookList() throws android.os.RemoteException;
  public void addBook(com.yey.ok.Book book) throws android.os.RemoteException;
}
