package concurrency.executors.and.future;


public class ArchiveSearcherImpl implements ArchiveSearcher {

    public String search (String target) throws InterruptedException {
        Thread.sleep(5000);
        return "search result which take long time";
    }
}
