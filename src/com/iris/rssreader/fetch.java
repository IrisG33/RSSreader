package com.iris.rssreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author shanshangao
 */
public class fetch extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String topic = request.getParameter("topic");
            String source = request.getParameter("source");
            System.out.println("%%%%%%"+topic);
            System.out.println("%%%%%"+source);
  
            String feeds= null;


            response.setContentType("text/html;charset=UTF-8");

            // get the xsl stored in this project
            ServletContext context = getServletContext();
            InputStream xsl = (InputStream) (context.getResourceAsStream("/xslStyleSheet.xsl"));

            // two sourse objects and one result
            // get an enternal xml document using a url in a string format

            Source xmlDoc = new StreamSource("http://google.com");
            URL url;
            if (source.equals("NYTimes")) {
                xmlDoc = new StreamSource("http://rss.nytimes.com/services/xml/rss/nyt/" + topic + ".xml");
                url = new URL("http://rss.nytimes.com/services/xml/rss/nyt/" + topic + ".xml");
            } else if ("Sydney".equals(source)) {
                xmlDoc = new StreamSource("http://rss.feedsportal.com/c/34697/fe.ed/www.smh.com.au/rssheadlines/" + topic.toLowerCase() + ".xml");
                url = new URL("http://rss.feedsportal.com/c/34697/fe.ed/www.smh.com.au/rssheadlines/" + topic.toLowerCase() + ".xml");
            } else if ("BBC".equals(source)) {
                xmlDoc = new StreamSource("http://feeds.bbci.co.uk/news/" + topic.toLowerCase() + "/rss.xml");
                url = new URL("http://feeds.bbci.co.uk/news/" + topic.toLowerCase() + "/rss.xml");
            } else {
                xmlDoc = new StreamSource();
                url = new URL("http://google.com");
            }

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int statusCode = connection.getResponseCode();

            Source xslDoc = new StreamSource(xsl);
            StringWriter outWriter = new StringWriter();
            //StreamResult result = new StreamResult(out);
            
            StreamResult result = new StreamResult(outWriter);
 
            // Prepare to transform
            TransformerFactory factory = TransformerFactory.newInstance();
            try {
                Transformer trans = factory.newTransformer(xslDoc);
                if (statusCode == 200) {
                    trans.transform(xmlDoc, result);
                    StringBuffer sb = outWriter.getBuffer();
                    feeds = sb.toString();
                    response.setContentType("text/xml");
                    response.getWriter().write(feeds);
                } else {
                    out.println("Error: " + statusCode);
                }

            } catch (Exception e) {
                System.out.println("Exceptions in factory@@@" + e.toString());
            }

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
