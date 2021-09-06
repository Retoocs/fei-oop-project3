package app.utils.domain;

import java.time.LocalDate;

public class Season {
    private LocalDate beginningDate;
    private LocalDate endingDate;

    public Season(LocalDate beginningDate, LocalDate endingDate){
        setBeginningDate(beginningDate);
        setEndingDate(endingDate);
    }

    public LocalDate getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(LocalDate beginningDate) {
        if(beginningDate == null){
            throw new IllegalArgumentException("'beginningDate' is null.");
        }
        this.beginningDate = beginningDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        if(endingDate == null){
            throw new IllegalArgumentException("'endingDate' is null.");
        }
        this.endingDate = endingDate;
    }
}
