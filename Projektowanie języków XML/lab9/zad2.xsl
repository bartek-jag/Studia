<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes" encoding="utf-8"/>
    <xsl:template match="/">
        <lista>
            <xsl:comment> Osoby młodsze niż 30 lat </xsl:comment>
            <xsl:for-each select="lista/osoba">
                <xsl:if test="wiek < 30">
                    <xsl:element name="osoba">
                        <xsl:element name="imie">
                            <xsl:value-of select="imie"/>
                        </xsl:element>
                        <xsl:element name="nazwisko">
                            <xsl:value-of select="nazwisko"/>
                        </xsl:element>
                    </xsl:element>
                </xsl:if>
            </xsl:for-each>
        </lista>
    </xsl:template>
</xsl:stylesheet>