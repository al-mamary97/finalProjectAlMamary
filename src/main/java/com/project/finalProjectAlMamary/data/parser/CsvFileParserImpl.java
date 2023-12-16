package com.project.finalProjectAlMamary.data.parser;

import static com.project.finalProjectAlMamary.data.parser.CsvFileParserUtils.COLUMN_SEPARATOR;
import static com.project.finalProjectAlMamary.data.parser.CsvFileParserUtils.MODULES_TITLES_COLUMN_NUMBER_ARRAY;
import static com.project.finalProjectAlMamary.data.parser.CsvFileParserUtils.moduleScoreTotalPointsColumnNumberArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.project.finalProjectAlMamary.data.parser.model.ModuleScoreDto;
import com.project.finalProjectAlMamary.data.parser.model.StudentDto;

@Service
public class CsvFileParserImpl implements CsvFileParser
{
    @Override
    public List<StudentDto> parseDataFromCsvFile(Path filePath)
    {
        CSVReader csvReader = getReader(filePath);
        List<StudentDto> studentDtos = getData(csvReader);
        try
        {
            csvReader.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return studentDtos;
    }

    @Override
    public List<ModuleScoreDto> getMaxScores(Path filePath)
    {
        CSVReader csvReader = getReader(filePath);

        try
        {
            List<String> modulesTitles = getModulesTitles(csvReader);
            csvReader.readNext(); // skip second line

            List<ModuleScoreDto> moduleScoreDtos = getModulesScores(csvReader.readNext(), modulesTitles);
            csvReader.close();
            return moduleScoreDtos;
        }
        catch (CsvValidationException | IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private CSVReader getReader(Path filePath)
    {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath.toString());

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(COLUMN_SEPARATOR)
                .withIgnoreQuotations(true)
                .build();

        Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream));
        return new CSVReaderBuilder(reader)
                .withCSVParser(parser)
                .build();
    }

    private List<StudentDto> getData(CSVReader csvReader)
    {
        try
        {
            List<String> modulesTitles = getModulesTitles(csvReader);
            csvReader.readNext(); // skip second line
            csvReader.readNext(); // skip third line with maxScores

            return getStudents(csvReader, modulesTitles);
        }
        catch (CsvValidationException | IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private List<String> getModulesTitles(CSVReader csvReader) throws CsvValidationException, IOException
    {
        String[] firstLine = csvReader.readNext();

        return Arrays.stream(MODULES_TITLES_COLUMN_NUMBER_ARRAY)
                .map(columnNumber -> firstLine[columnNumber - 1])
                .toList();
    }

    private List<StudentDto> getStudents(CSVReader csvReader, List<String> modulesTitles)
            throws CsvValidationException, IOException
    {
        List<StudentDto> students = new ArrayList<>();
        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null)
        {
            students.add(getStudent(nextLine, modulesTitles));
        }

        return students;
    }

    private static StudentDto getStudent(String[] line, List<String> modulesTitles)
    {
        return getStudentInfo(line)
                .setModuleScoreDtos(getModulesScores(line, modulesTitles));
    }

    private static List<ModuleScoreDto> getModulesScores(String[] line, List<String> modulesTitles)
    {
        AtomicInteger indexOfArray = new AtomicInteger();
        return Arrays.stream(moduleScoreTotalPointsColumnNumberArray).map(totalPointsColumnNumbers ->
                {
                    ModuleScoreDto moduleScoreDto = new ModuleScoreDto()
                            .setTitle(modulesTitles.get(indexOfArray.getAndIncrement()))
                            .setActivityPoints(
                                    totalPointsColumnNumbers[0] != null
                                            ? Integer.parseInt(line[totalPointsColumnNumbers[0] - 1])
                                            : 0)
                            .setExercisePoints(
                                    totalPointsColumnNumbers[1] != null
                                            ? Integer.parseInt(line[totalPointsColumnNumbers[1] - 1])
                                            : 0)
                            .setHomeworkPoints(
                                    totalPointsColumnNumbers[2] != null
                                            ? Integer.parseInt(line[totalPointsColumnNumbers[2] - 1])
                                            : 0)
                            .setSeminarPoints(
                                    totalPointsColumnNumbers[3] != null
                                            ? Integer.parseInt(line[totalPointsColumnNumbers[3] - 1])
                                            : 0);

                    moduleScoreDto.setSumPoints(
                            moduleScoreDto.getActivityPoints()
                                    + moduleScoreDto.getExercisePoints()
                                    + moduleScoreDto.getHomeworkPoints()
                                    + moduleScoreDto.getSeminarPoints()
                    );

                    return moduleScoreDto;
                })
                .toList();
    }

    private static StudentDto getStudentInfo(String[] line)
    {
        return new StudentDto()
                .setFullName(line[0])
                .setGroupTitle(line[1])
                .setAge(-1)
                .setCityTitle("");
    }
}
