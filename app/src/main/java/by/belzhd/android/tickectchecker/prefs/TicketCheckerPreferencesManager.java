package by.belzhd.android.tickectchecker.prefs;

import android.content.Context;

public class TicketCheckerPreferencesManager extends BasePreferencesManager {

    private static final String PREFS_FILE_NAME = "checker_prefs";
    private static final String PREFS_CURRENT_ROUTE = "PREFS_CURRENT_ROUTE";
    private static final String PREFS_EMBARKATION_STARTED = "PREFS_EMBARKATION_STARTED";
    private static final String PREFS_DISEMBARKATION_STARTED = "PREFS_DISEMBARKATION_STARTED";

    public TicketCheckerPreferencesManager(Context context) {
        super(context, PREFS_FILE_NAME);
    }

    public int getCurrentRoute() {
        return getInt(PREFS_CURRENT_ROUTE, -1);
    }

    public void setCurrentRoute(final int currentRoute) {
        setInt(PREFS_CURRENT_ROUTE, currentRoute);
    }

    public void setIsEmbarkationStarted(final boolean isStarted) {
        setBoolean(PREFS_EMBARKATION_STARTED, isStarted);
    }

    public boolean getIsEmbarkationStarted() {
        return getBoolean(PREFS_EMBARKATION_STARTED, false);
    }

    public void setIsDisembarkationStarted(final boolean isStarted) {
        setBoolean(PREFS_DISEMBARKATION_STARTED, isStarted);
    }

    public boolean getIsDisembarkationStarted() {
        return getBoolean(PREFS_DISEMBARKATION_STARTED, false);
    }
}
