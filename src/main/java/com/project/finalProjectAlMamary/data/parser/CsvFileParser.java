package com.project.finalProjectAlMamary.data.parser;

import java.nio.file.Path;
import java.util.List;

import com.project.finalProjectAlMamary.data.parser.model.ModuleScoreDto;
import com.project.finalProjectAlMamary.data.parser.model.StudentDto;

public interface CsvFileParser
{
    List<StudentDto> parseDataFromCsvFile(Path filePath);

    List<ModuleScoreDto> getMaxScores(Path filePath);
}
