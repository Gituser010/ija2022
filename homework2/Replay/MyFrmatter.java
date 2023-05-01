package ija.ija2022.homework2.Replay;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFrmatter extends Formatter {
    @Override
    public String format(LogRecord logRecord) {
        return logRecord.getMessage() + "\n";
    }
}
