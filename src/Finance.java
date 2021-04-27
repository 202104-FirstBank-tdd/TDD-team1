import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Finance {
    private BudgetRepo repo;

    public Finance(BudgetRepo repo) {
        this.repo = repo;
    }

    public double queryBudget(LocalDate start, LocalDate end) {
//        java.time.Duration duration = java.time.Duration.between(start, end);
//        betwPeriod.between(start, end).getDays();
        String month = String.valueOf(start.getMonthValue());
//        long betwwnDays = duration.toDays();
        int startdays = start.lengthOfMonth();
//        double ratio = betwwnDays/(double)startdays;
        YearMonth startMonth = YearMonth.from(start);
        YearMonth endMonth = YearMonth.from(end);
        double result = 0;
        for (Budget budget : repo.getAll()) {
            YearMonth budgetMonth = YearMonth.parse(budget.getYearMonth(), DateTimeFormatter.ofPattern("yyyyMM"));
            if(budgetMonth.equals(startMonth) && budgetMonth.equals(endMonth)){
                int days = Period.between(start, end).getDays();
                int allDays = budgetMonth.lengthOfMonth();
                double ratio = days / (double) allDays;
                result += budget.getAmount()*ratio;
            }
            if(budgetMonth.equals(startMonth)){
                result = result + budget.getAmount();
            }else if(budgetMonth.equals(endMonth)){
                result = result + budget.getAmount();
            }

        }
        return result;
    }

    public double queryBudgettwo(LocalDate start, LocalDate end) {
        return repo.getAll().get(0).amount;
//        return 0;
    }
}
