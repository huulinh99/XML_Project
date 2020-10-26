<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>
    <xsl:template match="/">
        <categories>
            <xsl:for-each select="//nav/ul/li">
                <category>
                    <name>
                        <xsl:value-of select="a" />
                    </name>
                    <url>
                        <xsl:value-of select="a/@href" />
                    </url>
                    <xsl:for-each select="./div/div/div/ul/li">
                        <subcategory>
                            <name>
                                <xsl:value-of select="a" />
                            </name>
                            <url>
                                <xsl:value-of select="a/@href" />
                            </url>
                        </subcategory>
                    </xsl:for-each>
                </category>
            </xsl:for-each>
        </categories>
    </xsl:template>
</xsl:stylesheet>