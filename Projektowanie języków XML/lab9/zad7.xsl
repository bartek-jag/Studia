<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes" encoding="utf-8"/>
    <xsl:key name="grupaID" match="osoba" use="grupa"/>
    <xsl:template match="lista">
        <html>
            <head>
            </head>
            <body>
                <xsl:for-each select="osoba[string-length(nazwisko)+string-length(imie) > 12]">
                    <xsl:value-of select="imie"/>
                    <b>
                        <xsl:text></xsl:text>
                        <xsl:value-of select="nazwisko"/>
                    </b>
                    <xsl:text></xsl:text>
                    <u>grupa:</u>
                    <xsl:text></xsl:text>
                    <i>
                        <xsl:value-of select="grupa"/>
                    </i>
                    <br/>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>