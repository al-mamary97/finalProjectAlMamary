package com.project.finalProjectAlMamary.data.parser;

public final class CsvFileParserUtils
{
    private CsvFileParserUtils()
    {
    }

    public static final Integer[][] moduleScoreTotalPointsColumnNumberArray = {
            { 9, 10, null, 22 },
            { 23, 24, 32, 37 },
            { 38, 39, 45, 49 },
            { 50, 51, 56, 61 },
            { 62, 63, 74, 78 },
            { 79, 80, 89, 93 },
            { 94, 95, 99, 103 },
            { 104, 105, 111, 116 },
            { 117, 118, 123, 126 },
            { 127, 128, 133, 137 },
            { 138, null, 139, 144 },
            { 145, 146, 155, 160 },
            { 161, 162, 172, 176 },
            { 177, 178, 184, 188 },
            { 189, 190, 195, 198 }
    };

    public static final Integer[] MODULES_TITLES_COLUMN_NUMBER_ARRAY = { 9, 23, 38, 50, 62, 79,
            94, 104, 117, 127, 138, 145, 161, 177, 189 };

    public static final Character COLUMN_SEPARATOR = ';';
}
