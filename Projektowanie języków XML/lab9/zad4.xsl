<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes" encoding="utf-8"/>
    <xsl:template match="/">
        <html>
            <body>
                <ol>
                    <xsl:for-each select="lista/osoba">
                        <li>
                            <xsl:choose>
                                <xsl:when test="grupa='1'">
                                    <span style="color: red;">
                                        <xsl:element name="osoba">
                                            <xsl:value-of select="imie"/>
                                            <xsl:text></xsl:text>
                                            <xsl:value-of select="nazwisko"/>
                                        </xsl:element>
                                    </span>
                                </xsl:when>
                                <xsl:when test="grupa='2'">
                                    <span style="color: green;">
                                        <xsl:element name="osoba">
                                            <xsl:value-of select="imie"/>
                                            <xsl:text></xsl:text>
                                            <xsl:value-of select="nazwisko"/>
                                        </xsl:element>
                                    </span>
                                </xsl:when>
                                <xsl:when test="grupa='3'">
                                    <span style="color: blue;">
                                        <xsl:element name="osoba">
                                            <xsl:value-of select="imie"/>
                                            <xsl:text></xsl:text>
                                            <xsl:value-of select="nazwisko"/>
                                        </xsl:element>
                                    </span>
                                </xsl:when>
                            </xsl:choose>
                        </li>
                    </xsl:for-each>
                </ol>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>