<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:def="urn:schemas-microsoft-com:office:spreadsheet"
                xmlns:o="urn:schemas-microsoft-com:office:office"
                xmlns:x="urn:schemas-microsoft-com:office:excel"
                xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
                xmlns:html="http://www.w3.org/TR/REC-html40"
                xmlns:x2="http://schemas.microsoft.com/office/excel/2003/xml"
    exclude-result-prefixes="def o x ss html x2" >
    <xsl:output indent="yes" method="xml" />
    <xsl:template match="/">
        <test>
            <questions>
                <xsl:apply-templates select="/def:Workbook/def:Worksheet[@ss:Name='questions']"/>
            </questions>
            <answers>
                <xsl:apply-templates select="/def:Workbook/def:Worksheet[@ss:Name='answers']"/>
            </answers>
        </test>
    </xsl:template>


    
    <xsl:template match="def:Worksheet[@ss:Name='questions']">
        <xsl:for-each select="def:Table/def:Row">
            <question>
                <xsl:attribute name="qnum">
                    <xsl:value-of select="def:Cell[1]"/>
                </xsl:attribute>
                <xsl:value-of select="def:Cell[2]"/>
            </question>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="def:Worksheet[@ss:Name='answers']">
        <xsl:for-each select="def:Table/def:Row">
            <answer>
                <xsl:attribute name="qnum">
                    <xsl:value-of select="def:Cell[1]"/>
                </xsl:attribute>
                    <xsl:if test="def:Cell[2]/def:Data='V'">
                        <xsl:attribute name="true"/>
                    </xsl:if>

                <xsl:value-of select="def:Cell[3]"/>
            </answer>
        </xsl:for-each>
    </xsl:template>


</xsl:stylesheet>