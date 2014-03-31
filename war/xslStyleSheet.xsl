<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : xslStyleSheet.xsl
    Created on : September 21, 2013, 11:35 AM
    Author     : shanshangao
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>
    <xsl:template match="/">
        
        <!--html-->
            <!--head>
                <title>News</title>
            </head>
            <body-->
                <ul>
                <xsl:for-each select="rss/channel/item">
                    <li>
                        <a href="{link}"><xsl:value-of select="title"/></a>
                    </li>
                </xsl:for-each>
                </ul>
            <!--/body-->
        <!--/html-->
    </xsl:template>

</xsl:stylesheet>
