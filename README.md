# GettingPseudorangesGNSSDataAndroid
Using GNSS raw measurements on android devices. In May 2016, Google announced the availability of GNSS raw measurements from Android 7.

<img width="1114" height="627" alt="image" src="https://github.com/user-attachments/assets/84695f07-de89-4905-8e19-b59749660615" />

Navigation Processing Flow
Android API
    ↓
    ├─→ Navigation Data → Decoder → Satellite ephemeris, clock correction & ionospheric corrections parameters...
    │                                                       ↓
    └─→ Raw measurements → Pseudorange, phase, CNO...
                                                       ↓
                                                 PVT Engine

<img width="1102" height="341" alt="image" src="https://github.com/user-attachments/assets/d5089751-9a2f-4545-b6a9-63a18b377274" />

## How to obtain GNSS parameters from the raw measurements (android.location)


| Class | Method | Description |
| :--- | :--- | :--- |
| **GnssNavigationMessage** | `getData()` | Gets the data of the reported GNSS message. |
| | `getType()` | Gets the type of the navigation message contained in the object |
| | `getSubmessageId()` | Gets the sub-message identifier. For Galileo F/NAV refers to the page type in the range 1-6. For Galileo I/NAV refers to the word type in the range 1-10+. |
| **GnssClock** | `getFullBiasNanos` | Tells you the difference between hardware clock and the true GPS time since January 6, 1980. |
| | `getTimeNanos()` | It tells you the time in nanoseconds of the hardware clock inside GNSS receiver. |
| | `getTimeOffsetNanos()` | It gets the time offset at which the measurement was taken in nanoseconds.. |
| **GnssMeasurement** | `getConstellationType()` | It tells you which of the different GNSS constellations a particular satellite belongs to. |
| | `getCarrierFrequencyHz()` | It tells you whether you are on the L1 or the L5 band for a particular signal. |
| | `getAccumulatedDeltaRangeMeters()` | This method indicates how far along that carrier wave the receiver has tracked you since it began tracking the signal. |
| | `getReceivedSvTimeNanos()` | It estimates the time of transmission of the pseudorange. |

