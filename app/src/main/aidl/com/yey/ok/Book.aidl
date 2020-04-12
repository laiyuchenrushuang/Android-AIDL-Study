package com.yey.ok;
// 如果AIDL中用到了自定义Parcelable类对象(Book.java)，
// 那么必须新建一个和她同名的AIDL文件(Book.aidl)，
// 并且在其中申明它为parcelable类型
parcelable Book;
