<%-- 
    Document   : index
    Created on : Sep 21, 2013, 2:36:57 AM
    Author     : shanshangao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reading RSS</title>
        <script type="text/javascript" language="javascript" src="ajaxRSS.js"></script>
        <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
        <script type="text/javascript">$(document).ready(function(){
            
        })</script>
    </head>
    <body>
        <h4>Choose the topic and source you are interested in!</h4>
        <!--form-->
            <select id="topics" onchange="choose()">
                <option value="Business">Business</option>
                <option value="Technology">Technology</option>
                <option value="World">World News</option>
            </select>

            <select id="sources" onchange="choose()">
                <option value="BBC">BBC</option>
                <option value="Sydney">Sydney Morning Herald</option>
                <option value="NYTimes">New York Times</option>
            </select>
            <br>
            <!--input type="submit" name="trans" value="Find feeds"-->
        <!--/form-->
        <ul id="feeds">
        </ul>
    </body>
</html>
