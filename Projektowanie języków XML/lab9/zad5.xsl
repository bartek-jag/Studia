<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes" encoding="utf-8"/>
    <xsl:template match="lista">
    <style>
        .k {color: red;}
        .m {color: blue;}
    </style>
                <ul>
                    <xsl:for-each select="osoba[@plec='k']">
                    <xsl:sort select="wiek" order="descending"/>
                        <li><span class="k"><xsl:value-of select="imie"/><xsl:text> </xsl:text><xsl:value-of select="nazwisko"/></span></li>
                    </xsl:for-each>
                    <xsl:for-each select="osoba[@plec='m']">
                    <xsl:sort select="wiek" order="descending"/>
                        <li><span class="m"><xsl:value-of select="imie"/><xsl:text> </xsl:text><xsl:value-of select="nazwisko"/></span></li>
                    </xsl:for-each>
                </ul>
    </xsl:template>
</xsl:stylesheet>