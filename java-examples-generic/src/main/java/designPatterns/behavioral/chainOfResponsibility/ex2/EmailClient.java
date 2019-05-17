package designPatterns.behavioral.chainOfResponsibility.ex2;

/**
 * Created by DannyG on 08/01/2015.
 */
//email client

//If new rules, for forwarding email to particular folders are added,
// we can add the handler to our email processor at runtime using the addRule()
// method in the client.

public class EmailClient
{
    private EmailProcessor processor;

    public EmailClient()
    {
        createProcessor();

    }

    private void createProcessor()
    {
        System.out.println("creating processor\n" );
        processor = new EmailProcessor();
        processor.addHandler(new BusinessMailHandler());
        processor.addHandler(new GMailHandler());
        processor.addHandler(new LegalMailHandler());
        System.out.println("Finish creating processor\n" );
    }


    public void addRule(EmailHandler handler)
    {
        processor.addHandler(handler);
    }

    public void emailReceived(Email email)
    {
        System.out.println("\nProcessing email from:" +email.getFrom()+"\n" );
        processor.handleRequest(email);
    }



    public static void main(String[] args)
    {
        String from = "bla@businessaddress.com";
        Email email = new Email(from);
        EmailClient client = new EmailClient();

        client.emailReceived(email);
    }

}
