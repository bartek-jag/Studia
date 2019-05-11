<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes" encoding="utf-8"/>
    <xsl:key name="grupaID" match="osoba" use="grupa"/>
    <xsl:template match="lista">
        <html>
            <head>
            </head>
            <body>
                    <b>Ilość wszystkich osób: </b><xsl:value-of select="count(osoba)"/><br/>


                    <xsl:for-each select="osoba[count(. | key('grupaID', grupa)[1]) = 1]">
                        <b>Ilość osób z grupy <xsl:value-of select="grupa"/>: </b><xsl:value-of select="count(key('grupaID', grupa))"/><br/>
                    </xsl:for-each>

                    <b>Ilość kobiet: </b><xsl:value-of select="count(osoba[@plec='k'])"/><br/>
                    <b>Ilość mężczyzn: </b><xsl:value-of select="count(osoba[@plec='m'])"/><br/>
                    <b>Średnia wieku wszystkich osób: </b><xsl:value-of select="sum(osoba/wiek) div count(osoba)"/><br/>

                    <xsl:for-each select="osoba[count(. | key('grupaID', grupa)[1]) = 1]">
                        <xsl:sort select="key('grupaID', grupa)/grupa"/>
                        <b>Średnia wieku osób z grupy <xsl:value-of select="grupa"/>: </b><xsl:value-of select="round(sum(key('grupaID', grupa)/wiek) div count(key('grupaID', grupa)) * 100) div 100"/><br/>
                    </xsl:for-each>

                    <b>Średnia wieku kobiet: </b><xsl:value-of select="sum(osoba[@plec='k']/wiek) div count(osoba[@plec='k'])"/><br/>
                    <b>Średnia wieku mężczyzn: </b><xsl:value-of select="sum(osoba[@plec='m']/wiek) div count(osoba[@plec='m'])"/><br/>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>