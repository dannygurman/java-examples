package concurrency.executors.and.future;

public interface ArchiveSearcher {

    String search (String target) throws InterruptedException;
}
