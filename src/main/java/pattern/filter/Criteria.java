package pattern.filter;

import java.util.List;

public interface Criteria {
    List<Person> meetCriteria(List<Person> people);
}