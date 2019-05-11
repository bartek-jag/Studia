<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes" encoding="utf-8"/>
    <xsl:key name="grupaID" match="osoba" use="grupa"/>
    <xsl:template match="lista">
        <html>
            <head>
                <style>
        tr:nth-child(even) {background: yellow;}
        tr:nth-child(odd) {background: cyan;}
    </style>
            </head>
            <body>
                <xsl:for-each select="osoba[count(. | key('grupaID', grupa)[1]) = 1]">
                    <h2>Grupa <xsl:value-of select="grupa"/>
                    </h2>
                    <table>
                        <xsl:for-each select="key('grupaID', grupa)">
                            <tr>
                                <td>
                                    <xsl:value-of select="imie"/>
                                    <xsl:value-of select="nazwisko"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>