<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes" encoding="utf-8"/>
    <xsl:template match="/">
        <lista>
            <xsl:for-each select="lista/osoba">
                <xsl:sort select="nazwisko"/>
                <xsl:element name="osoba">
                    <xsl:element name="imie">
                        <xsl:value-of select="imie"/>
                    </xsl:element>
                    <xsl:element name="nazwisko">
                        <xsl:value-of select="nazwisko"/>
                    </xsl:element>
                    <xsl:element name="plec">
                        <xsl:choose>
                            <xsl:when test="@plec='k'">Kobieta</xsl:when>
                            <xsl:otherwise>Mężczyzna</xsl:otherwise>
                        </xsl:choose>
                    </xsl:element>
                </xsl:element>
            </xsl:for-each>
        </lista>
    </xsl:template>
</xsl:stylesheet>