package com.project.finalProjectAlMamary;

import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.finalProjectAlMamary.data.analyze.AnalyzeService;
import com.project.finalProjectAlMamary.data.parser.CsvFileParser;
import com.project.finalProjectAlMamary.data.parser.model.ModuleScoreDto;
import com.project.finalProjectAlMamary.data.parser.model.StudentDto;
import com.project.finalProjectAlMamary.data.service.CombineCsvAndVkData;
import com.project.finalProjectAlMamary.data.service.DataDbService;
import com.project.finalProjectAlMamary.data.service.vk.VkDataService;

@Component
public class ApplicationRunner
{
    private final CsvFileParser csvFileParser;
    private final VkDataService vkDataService;
    private final DataDbService dataDbService;
    private final AnalyzeService analyzeService;

    @Autowired
    public ApplicationRunner(CsvFileParser csvFileParser,
            VkDataService vkDataService,
            DataDbService dataDbService,
            AnalyzeService analyzeService)
    {
        this.csvFileParser = csvFileParser;
        this.vkDataService = vkDataService;
        this.dataDbService = dataDbService;
        this.analyzeService = analyzeService;
    }

    public void run()
    {
        Path pathToCsvFile = Path.of("static", "basicprogramming_2_1.csv");

        List<StudentDto> studentsDto = csvFileParser.parseDataFromCsvFile(pathToCsvFile);

        CombineCsvAndVkData.combine(studentsDto, vkDataService.getGroupMembers("project__it"));
        studentsDto.forEach(System.out::println);
        studentsDto.forEach(dataDbService::save);

        analyzeService.analyzeData(csvFileParser.getMaxScores(pathToCsvFile));
    }
}
