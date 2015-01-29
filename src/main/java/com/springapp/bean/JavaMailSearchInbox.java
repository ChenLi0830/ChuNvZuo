package com.springapp.bean;

/**
 * Created by root on 1/29/15.
 */
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.*;

public class JavaMailSearchInbox {

    public static void main(String args[]) throws Exception {

        // mail server info
        String host = "imap.gmail.com";
        String user = "ryan.lee0830@gmail.com";
        String password = "Geoffery0830";
        String keyword = "Ebay";

        // connect to my pop3 inbox in read-only mode
        Properties properties = System.getProperties();
        Session session = Session.getDefaultInstance(properties);
        Store store = session.getStore("imap");
        store.connect(host, user, password);
        Folder inbox = store.getFolder("inbox");
        inbox.open(Folder.READ_ONLY);

        // creates a search criterion
//        SearchTerm searchCondition = new SearchTerm() {
//            @Override
//            public boolean match(Message message) {
//                try {
//                    if (message.getSubject().contains(keyword)) {
//                        return true;
//                    }
//                } catch (MessagingException ex) {
//                    ex.printStackTrace();
//                }
//                return false;
//            }
//        };

        SearchTerm st = new AndTerm(
                new SubjectTerm("Ebay"),
                new BodyTerm("Ebay"));

        Message[] messages = inbox.search(st);

        // performs search through the folder
//        Message[] foundMessages = inbox.search(searchCondition);

        // search for all "unseen" messages


//        Flags seen = new Flags(Flags.Flag.SEEN);
//        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
//        Message messages[] = inbox.search(unseenFlagTerm);

        if (messages.length == 0) System.out.println("No messages found.");

        for (int i = 0; i < messages.length; i++) {
            // stop after listing ten messages
            if (i > 10) {
                System.exit(0);
                inbox.close(true);
                store.close();
            }

            System.out.println("Message " + (i + 1));
            System.out.println("From : " + messages[i].getFrom()[0]);
            System.out.println("Subject : " + messages[i].getSubject());
            System.out.println("Sent Date : " + messages[i].getSentDate());
            System.out.println();
        }

        inbox.close(true);
        store.close();
    }
}