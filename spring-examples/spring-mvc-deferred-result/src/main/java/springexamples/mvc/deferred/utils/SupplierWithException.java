package springexamples.mvc.deferred.utils;

@FunctionalInterface
public interface SupplierWithException <T> {
    T get() throws Exception;
}
