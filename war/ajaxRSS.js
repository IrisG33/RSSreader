/*
 * Returns an new XMLHttpRequest object, or false if the browser
 * doesn't support it
 */

function choose() {
    
    //apply jquery to get topic and source from DOM
    var topic = $("#topics :selected").val();
    var source = $("#sources :selected").val();
    var request = newXMLHttpRequest();
    request.onreadystatechange = getReadyStateHandler(request, updateFeeds);
    request.open("POST", "fetch", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.send("topic=" + topic + "&source=" + source);
}

function updateFeeds(resHtml) {

    var whole = resHtml.getElementsByTagName("ul")[0];
    var f = document.getElementById("feeds");
    f.innerHTML = ""; 
    var feeds = whole.getElementsByTagName("li");
    for (var I = 0 ; I < feeds.length ; I++){
        var feed = feeds[I].getElementsByTagName("a")[0];
        var href = feed.getAttribute("href");
        var title = feed.firstChild.nodeValue;
        var listFeed = document.createElement("li");
        var listLink = document.createElement('a');
        $(listLink).attr('href', href);
        listFeed.appendChild(listLink);
        listLink.appendChild(document.createTextNode(title));
        f.appendChild(listFeed);
    }
}
function newXMLHttpRequest() {

    var xmlreq = false;

    // Create XMLHttpRequest object in non-Microsoft browsers
    if (window.XMLHttpRequest) {
        xmlreq = new XMLHttpRequest();

    } else if (window.ActiveXObject) {

        try {
            // Try to create XMLHttpRequest in later versions
            // of Internet Explorer

            xmlreq = new ActiveXObject("Msxml2.XMLHTTP");

        } catch (e1) {

            // Failed to create required ActiveXObject

            try {
                // Try version supported by older versions
                // of Internet Explorer

                xmlreq = new ActiveXObject("Microsoft.XMLHTTP");

            } catch (e2) {

                // Unable to create an XMLHttpRequest by any means
                xmlreq = false;
            }
        }
    }

    return xmlreq;
}

/*
 * Returns a function that waits for the specified XMLHttpRequest
 * to complete, then passes it XML response to the given handler function.
 * req - The XMLHttpRequest whose state is changing
 * responseXmlHandler - Function to pass the XML response to
 */
function getReadyStateHandler(req, responseXmlHandler) {

    // Return an anonymous function that listens to the XMLHttpRequest instance
    return function() {

        // If the request's status is "complete"
        if (req.readyState == 4) {

            // Check that we received a successful response from the server
            if (req.status == 200) {

                // Pass the XML payload of the response to the handler function.
                responseXmlHandler(req.responseXML);

            } else {

                // An HTTP problem has occurred
                alert("HTTP error " + req.status + ": " + req.statusText);
            }
        }
    }
}



