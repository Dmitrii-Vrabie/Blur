package hfad.com.cribforpascal.data;

import android.provider.BaseColumns;

/**
 * Created by Mephisto on 3/4/2017.
 */

public class CribForPascalContract {

    public static class CribForPascalEntry implements BaseColumns{
        public static final String TABLE_NAME = "crib";
        public static final String COLUMN_STATEMENT = "statement";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_STEP_BY_STEP = "step_by_step";
        public static final String COLUMN_EXERICISE = "exercise";
        public static final String COLUMN_ANSWER = "answer";
    }
}
