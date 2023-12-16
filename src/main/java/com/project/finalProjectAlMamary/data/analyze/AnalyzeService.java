package com.project.finalProjectAlMamary.data.analyze;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.finalProjectAlMamary.data.parser.model.ModuleScoreDto;
import com.project.finalProjectAlMamary.data.score.entity.ModuleScoreEntity;
import com.project.finalProjectAlMamary.data.service.DataDbService;
import com.project.finalProjectAlMamary.data.student.entity.StudentEntity;

@Service
public class AnalyzeService
{
    private final DataDbService dataDbService;

    @Autowired
    public AnalyzeService(DataDbService dataDbService)
    {
        this.dataDbService = dataDbService;
    }

    public void analyzeData(List<ModuleScoreDto> maxModuleScoreDtos)
    {
        averageHomeworkScoreBetweenGroups(maxModuleScoreDtos);
        averageHomeworkScoreBetweenAge(maxModuleScoreDtos);
        averageHomeworkScoreBetweenCity(maxModuleScoreDtos);
    }

    private void averageHomeworkScoreBetweenGroups(List<ModuleScoreDto> maxModuleScoreDtos)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Set<String> groupTitles = dataDbService.getGroups();
        for (String groupTitle : groupTitles)
        {
            List<StudentEntity> studentEntityList = dataDbService.getStudentsByGroup(groupTitle);
            for (ModuleScoreDto maxModuleScoreDto : maxModuleScoreDtos)
            {
                List<ModuleScoreEntity> studentsScoresByModule = studentEntityList.stream()
                        .map(student -> dataDbService.findByStudentAndModuleTitle(student, maxModuleScoreDto.getTitle()))
                        .toList();

                Double averageHomeworkScoreInGroup = studentsScoresByModule.stream()
                        .mapToDouble(ModuleScoreEntity::getHomeworkPoints)
                        .average()
                        .orElse(Double.NaN);

                dataset.setValue(averageHomeworkScoreInGroup, groupTitle, maxModuleScoreDto.getTitle());
            }
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Показатель среднего балла по домашнему заданию между группами",
                "Темы",
                "Средний балла у групп по домашнему заданию",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        chart.setBackgroundPaint(Color.black);
        chart.getTitle().setPaint(Color.white);
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        ((BarRenderer)categoryPlot.getRenderer()).setItemMargin(0);
        try
        {
            ChartUtilities.saveChartAsPNG(new File("averageGroup.png"), chart, 3500, 800);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void averageHomeworkScoreBetweenAge(List<ModuleScoreDto> maxModuleScoreDtos)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Set<Integer> ages = dataDbService.getAges();
        for (Integer age : ages)
        {
            List<StudentEntity> studentEntityList = dataDbService.getStudentsByAge(age);
            for (ModuleScoreDto maxModuleScoreDto : maxModuleScoreDtos)
            {
                List<ModuleScoreEntity> studentsScoresByModule = studentEntityList.stream()
                        .map(student -> dataDbService.findByStudentAndModuleTitle(student, maxModuleScoreDto.getTitle()))
                        .toList();

                Double averageHomeworkScoreInGroup = studentsScoresByModule.stream()
                        .mapToDouble(ModuleScoreEntity::getHomeworkPoints)
                        .average()
                        .orElse(Double.NaN);

                dataset.setValue(averageHomeworkScoreInGroup, age, maxModuleScoreDto.getTitle());
            }
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Показатель среднего балла по домашнему заданию между студентов разного возраста",
                "Темы",
                "Средний балла у студентов разного возраста по домашнему заданию",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        chart.setBackgroundPaint(Color.black);
        chart.getTitle().setPaint(Color.white);
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        ((BarRenderer)categoryPlot.getRenderer()).setItemMargin(0);
        try
        {
            ChartUtilities.saveChartAsPNG(new File("averageAge.png"), chart, 3500, 800);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void averageHomeworkScoreBetweenCity(List<ModuleScoreDto> maxModuleScoreDtos)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Set<String> cityTitles = dataDbService.getCities();
        for (String cityTitle : cityTitles)
        {
            List<StudentEntity> studentEntityList = dataDbService.getStudentsByCity(cityTitle);
            for (ModuleScoreDto maxModuleScoreDto : maxModuleScoreDtos)
            {
                List<ModuleScoreEntity> studentsScoresByModule = studentEntityList.stream()
                        .map(student -> dataDbService.findByStudentAndModuleTitle(student, maxModuleScoreDto.getTitle()))
                        .toList();

                Double averageHomeworkScoreInGroup = studentsScoresByModule.stream()
                        .mapToDouble(ModuleScoreEntity::getHomeworkPoints)
                        .average()
                        .orElse(Double.NaN);

                dataset.setValue(averageHomeworkScoreInGroup, cityTitle, maxModuleScoreDto.getTitle());
            }
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Показатель среднего балла по домашнему заданию между студентов разных городов",
                "Темы",
                "Средний балла у студентов разных городов по домашнему заданию",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        chart.setBackgroundPaint(Color.black);
        chart.getTitle().setPaint(Color.white);
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        ((BarRenderer)categoryPlot.getRenderer()).setItemMargin(0);
        try
        {
            ChartUtilities.saveChartAsPNG(new File("averageCity.png"), chart, 3500, 800);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
