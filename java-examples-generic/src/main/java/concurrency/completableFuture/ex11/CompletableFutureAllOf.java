package concurrency.completableFuture.ex11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CompletableFutureAllOf {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<String> webPageLinks = Arrays.asList(  "web1" , "web2");// A list of 100 web page links

        //Web content to future - running async
        Function<String, CompletableFuture<String> > linkToContentFutureMapper = webPageLink -> downloadWebPage(webPageLink) ;

        // Download contents of all the web pages asynchronously
        List<CompletableFuture<String>> pageContentFuturesList = webPageLinks.stream().map(linkToContentFutureMapper).collect(Collectors.toList());

        CompletableFuture [] pageContentFuturesArray =  pageContentFuturesList.toArray(new CompletableFuture[pageContentFuturesList.size()]);

        //Thread.sleep(2000);
        // Create a combined Future using allOf()
        CompletableFuture<Void> combinedAllFutures = CompletableFuture.allOf(pageContentFuturesArray);

        //The problem with CompletableFuture.allOf() is that it returns CompletableFuture<Void>.
        // But we can get the results of all the wrapped CompletableFutures:

        // When all the Futures are completed, call `future.join()` to get their results and collect the results in a list -

        //The join() method is similar to get(). The only difference is that it throws an unchecked exception if the underlying CompletableFuture completes exceptionally.
        Function < CompletableFuture<String> , String > pageContentFutureToContentFunction = pageContentFuture -> pageContentFuture.join();

        Function < Void , List<String> > getAllPageContentFromCombinedFutureFunction = v ->
                pageContentFuturesList.stream().map(pageContentFutureToContentFunction).collect(Collectors.toList());


        CompletableFuture<List<String>> allPageContentsFuture = combinedAllFutures.thenApply(getAllPageContentFromCombinedFutureFunction);

        List <String> webPages = allPageContentsFuture.get();
        webPages.forEach( page->System.out.println(page));


        Function < List<String> , Long> countWebPages = pageContents -> pageContents.stream().filter(pageContent -> pageContent.contains("web")).count();

        CompletableFuture<Long> countFuture = allPageContentsFuture.thenApply(countWebPages);

        System.out.println("Number of Web Pages having CompletableFuture keyword - " + countFuture.get());

        //Output:
        //downloaded_page_content of web1
        //downloaded_page_content of web2
        //Number of Web Pages having CompletableFuture keyword - 2

    }





    static  CompletableFuture<String>   downloadWebPage(String pageLink) {
        // Code to download and return the web page's content
        Supplier <String >  pageContentSupplier = () ->  "downloaded_page_content of "  + pageLink;
        return CompletableFuture.supplyAsync(pageContentSupplier);
    }
}
