import java.time.LocalDate;

public class Finance {
    private BudgetRepo repo;

    public Finance(BudgetRepo repo) {
        this.repo = repo;
    }

    public double queryBudget(LocalDate start, LocalDate end) {
        return repo.getAll().get(0).amount;
//        return 0;
    }
}
