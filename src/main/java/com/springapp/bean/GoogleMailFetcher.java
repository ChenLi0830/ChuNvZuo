package com.springapp.bean;

/**
 * Created by Chen on 15-01-29.
 */
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimePart;
import javax.mail.search.AndTerm;
import javax.mail.search.BodyTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

public class GoogleMailFetcher {
    private List<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();
    private PurchasedItem purchasedItem;

    public void fetch(String host, String storeType, String port, String user,
                             String password) {
        try {
            // create properties field
            Properties properties = new Properties();
            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", port);
            // SSL setting
            properties.setProperty("mail.imap.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.imap.socketFactory.fallback", "false");
            properties.setProperty("mail.imap.socketFactory.port",
                    String.valueOf(port));

            Session emailSession = Session.getDefaultInstance(properties);
//            emailSession.setDebug(true);

            // create the imap store object and connect with the pop server
            Store store = emailSession.getStore("imap");

            store.connect(user, password);

            // create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in));

            // retrieve the messages from the folder in an array and print it

            SearchTerm st = new AndTerm(
                    new SubjectTerm("Confirmation of your Order"),
                    new BodyTerm("Thank you for your purchase!"));

            Message[] messages = emailFolder.search(st);

            //System.out.println("messages.length---" + messages.length);

            for (int i = 0; i < messages.length; i++) {
                purchasedItem = new PurchasedItem();
                Message message = messages[i];
                //System.out.println("---------------------------------");
                writePart(message,"");
                purchasedItems.add(purchasedItem);
            }

            // close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    * This method checks for content-type
    * based on which, it processes and
    * fetches the content of the message
    */

    public void writePart(Part p, String ext) throws Exception {
        if (p instanceof Message)
            //Call methos writeEnvelope
            writeEnvelope((Message) p);

        //System.out.println("----------------------------");
        //System.out.println("CONTENT-TYPE: " + p.getContentType());

        //check if the content is plain text
        if (p.isMimeType("text/plain")) {
            //System.out.println("This is plain text");
            //System.out.println("---------------------------");
            String content = (String) p.getContent();
            //System.out.println(content);

            Pattern namePattern = Pattern.compile("Item name  (.*)");
            Matcher nameMatcher = namePattern.matcher(content);
            if (nameMatcher.find())
            {
                String itemName = nameMatcher.group(1);
                purchasedItem.setItemName(itemName);
            }

            Pattern urlPattern = Pattern.compile("Item URL:  (.*)");
            Matcher urlMatcher = urlPattern.matcher(content);
            if (urlMatcher.find())
            {
                String itemUrl = urlMatcher.group(1);
                purchasedItem.setItemURL(itemUrl);
            }

//            //System.out.println((String) p.getContent());
        } else if (p.isMimeType("text/html")){
            //System.out.println("This is html text");
            //System.out.println("---------------------------");
            String content = (String) p.getContent();
            //System.out.println(content);

            Pattern imgPattern = Pattern.compile("src=\"([^<>]*?)\" border=\"0\" width=\"64\" height=\"64\"");
            Matcher imgMatcher = imgPattern.matcher(content);
            if (imgMatcher.find())
            {
                String imgURL = imgMatcher.group(1);
                purchasedItem.setItemPicURL(imgURL);
            }
        }

        /*else if (p.isMimeType("multipart/related")) {
            Multipart mp = (Multipart)p.getContent();
//            Image image = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("image/jpeg")) {
                    writePart(bp, "jpg");
                    continue;
                }
                else if (bp.isMimeType("image/gif")) {
                    writePart(bp, "gif");
                    continue;
                }
                else if (bp.isMimeType("image/bmp")) {
                    writePart(bp, "bmp");
                    continue;
                }
                else if (bp.isMimeType("image/png")) {
                    writePart(bp, "png");
                    continue;
                }
            }
        }*/

        //check if the content has attachment
        else if (p.isMimeType("multipart/*")) {
            //System.out.println("This is a Multipart");
            //System.out.println("---------------------------");
            Multipart mp = (Multipart) p.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
                writePart(mp.getBodyPart(i),"");
        }
        //check if the content is a nested message
        else if (p.isMimeType("message/rfc822")) {
            //System.out.println("This is a Nested Message");
            //System.out.println("---------------------------");
            writePart((Part) p.getContent(),"");
        }

/*        //check if the content is an inline image
        else if (p.isMimeType("image*//*")) {
            //System.out.println("--------> image*//*");
//            Object o = p.getContent();

//            InputStream x = (InputStream) o;
            InputStream x = p.getInputStream();
            BufferedImage im = ImageIO.read(x);
            File imageFile = new File("/tmp/testImage2.jpg");
            ImageIO.write(im,"jpg",imageFile);
            x.close();

            // Construct the required byte array
            //System.out.println("x.length = " + x.available());
            int i = 0;
            byte[] bArray = new byte[x.available()];

            while ((i = (int) ((InputStream) x).available()) > 0) {
                int result = (int) (((InputStream) x).read(bArray));
                if (result == -1)
                    break;
            }
            FileOutputStream f2 = new FileOutputStream("/tmp/image.jpg");
            f2.write(bArray);
        }
        else if (p.getContentType().contains("image/")) {
            //System.out.println("content type" + p.getContentType());
            File f = new File("image" + new Date().getTime() + ".jpg");
            DataOutputStream output = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(f)));
            com.sun.mail.util.BASE64DecoderStream test =
                    (com.sun.mail.util.BASE64DecoderStream) p
                            .getContent();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = test.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }*/
        /*else {
            Object o = p.getContent();
            if (o instanceof String) {
                //System.out.println("This is a string");
                //System.out.println("---------------------------");
                //System.out.println((String) o);
            }
            else if (o instanceof InputStream) {
                //System.out.println("This is just an input stream");
                //System.out.println("---------------------------");
//                InputStream is = (InputStream) o;
//                is = (InputStream) o;
//                int c;
//                while ((c = is.read()) != -1)
//                    System.out.write(c);
            }
            else {
                //System.out.println("This is an unknown type");
                //System.out.println("---------------------------");
                //System.out.println(o.toString());
            }
        }*/

    }
    /*
    * This method would print FROM,TO and SUBJECT of the message
    */
    public static void writeEnvelope(Message m) throws Exception {
        //System.out.println("This is the message envelope");
        //System.out.println("---------------------------");
        Address[] a;

        // FROM
        if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("FROM: " + a[j].toString());
        }

        // TO
        if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("TO: " + a[j].toString());
        }

        // SUBJECT
        if (m.getSubject() != null)
            System.out.println("SUBJECT: " + m.getSubject());

    }

    public List<PurchasedItem> start(String username, String password) {
        String host = "imap.gmail.com";// change accordingly
        String mailStoreType = "imap";
        String port = "993";

        //Call method fetch
        fetch(host, mailStoreType, port, username, password);
        return purchasedItems;
    }

//    public static void main(String[] args) {
//
//        String host = "imap.gmail.com";// change accordingly
//        String mailStoreType = "imap";
//        String port = "993";
//        String username =
//                "lulugeo.li@gmail.com";// change accordingly
//        String password = "Wangwei19820510";// change accordingly
//
//        //Call method fetch
//        fetch(host, mailStoreType, port, username, password);
//    }
}