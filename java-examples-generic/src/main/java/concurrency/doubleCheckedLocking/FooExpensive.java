package concurrency.doubleCheckedLocking;

//Correct but possibly expensive multithreaded version
class FooExpensive {
 private Helper helper;
 public synchronized Helper getHelper() {
     if (helper == null) {
         helper = new Helper();
     }
     return helper;
 }

 // other functions and members...
}
