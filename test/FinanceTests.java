import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FinanceTests {

    private FakeRepo repo;
    private Finance finance;

    @Before
    public void setUp() throws Exception {
        repo = new FakeRepo();
        finance = new Finance(repo);
    }

    @Test
    public void query_whole_month() {
        repo.setBudgets(Arrays.asList(new Budget("202104",30)));
        double queryBudget = finance.queryBudget(
                LocalDate.of(2021, 4, 1),
                LocalDate.of(2021, 4, 30));
        assertEquals(30,queryBudget,0.00);


    }

    private class FakeRepo implements BudgetRepo {
        private List<Budget> budgets;

        @Override
        public List<Budget> getAll() {
            return budgets;
        }

        public void setBudgets(List<Budget> budgets) {
            this.budgets = budgets;
        }
    }
}
