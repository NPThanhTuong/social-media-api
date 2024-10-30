package com.one.social_media.controller;

import com.one.social_media.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/post")
    public String showStatistics(@RequestParam(value = "year", required = false) Integer selectedYear, Model model) {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

        if (selectedYear == null || selectedYear < 2020) {
            selectedYear = currentYear;
        }

        Map<Integer, Long> likesCount = statisticsService.getLikesCountByMonth(selectedYear);
        Map<Integer, Long> commentsCount = statisticsService.getCommentsCountByMonth(selectedYear);
        Map<Integer, Long> newUsersCount = statisticsService.getNewUsersCountByMonth(selectedYear);
        Map<Integer, Long> postsCount = statisticsService.getPostsCountByMonth(selectedYear);
        List<Integer> years = IntStream.rangeClosed(2020, currentYear).boxed().collect(Collectors.toList());

        model.addAttribute("year", selectedYear);
        model.addAttribute("likesCount", likesCount);
        model.addAttribute("commentsCount", commentsCount);
        model.addAttribute("newUsersCount", newUsersCount);
        model.addAttribute("postsCount", postsCount);
        model.addAttribute("years", years);
        model.addAttribute("selectedYear", selectedYear);

        return "statistics/view";
    }

}
