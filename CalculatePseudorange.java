import android.location.GnssClock;
import android.location.GnssMeasurementsEvent;

public class CalculatePseudorange {
    private long tRxGnss = 0;
    private double tRx = 0.0;
    private static final double SECONDS_PER_NANO = 1.0e-9;
    private long SpeedLight = 299792458;
    private double miliSecondsNumberNanos = 0.0;
    private double NumberSeconds100Milli = 604800e9 * 100;

    public void computePseudorange(GnssMeasurementsEvent event) {

        //Measurement time in GNSS time
        GnssClock gnssClock = event.getClock();
        tRxGnss = gnssClock.getTimeNanos() - (gnssClock.getFullBiasNanos() + getBiasNanos());

        for (GnssMeasurement measurement : event.getMeasurements()) { // ignore any measurement if it is not from Galileo constellation
            if (measurement.getConstellationType() != GnssStatus.CONSTELLATION_GALILEO) {
                continue;
            }

            // ignore raw data if TOW is not yet decoded and E1C 2nd code status lock
            if (measurement.getState() == STATE_TOW_DECODED) && (measurement.getState() == STATE_GAL_E1C_2ND_CODE_LOCK) {

                miliSecondsNumberNanos = math.floor(((gnssClock.getFullBiasNanos() * (-1)) / (NumberNanoSeconds100Milli)) * (NumberNanoSeconds100Milli));

                //Measurement time
                tRx = tRxGnss - miliSecondsNumberNanos;

                //Transmitted time
                long tTx = measurement.getReceivedSvTimeNanos() + measurement.getTimeOffsetNanos();

                //pseudorange
                long pseudorangeMilliSeconds = tRx - tTx;
                long pseudorange = pseudorangeMilliSeconds * SpeedLight * SECONDS_PER_NANO;
            }
        }
    }
}
