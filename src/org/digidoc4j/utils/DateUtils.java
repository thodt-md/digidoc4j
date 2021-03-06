/* DigiDoc4J library
*
* This software is released under either the GNU Library General Public
* License (see LICENSE.LGPL).
*
* Note that the only valid version of the LGPL license as far as this
* project is concerned is the original GNU Library General Public License
* Version 2.1, February 1999
*/

package org.digidoc4j.utils;

import static org.apache.commons.lang3.time.DateUtils.addSeconds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public final class DateUtils {
  private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

  private DateUtils() {
    logger.debug("");
  }

  /**
   * Checks is date in range of sixty seconds
   *
   * @param date to compare
   * @return true if the date is within 60 seconds, otherwise false
   */
  public static boolean isAlmostNow(Date date) {
    boolean inRange = isInRangeOneMinute(new Date(), date);
    logger.debug("Is almost now: " + inRange);
    return inRange;
  }

  private static boolean isInRangeOneMinute(Date date1, Date date2) {
    logger.debug("");
    final int oneMinuteInSeconds = 60;
    return isInRangeSeconds(date1, date2, oneMinuteInSeconds);
  }

  public static boolean isInRangeMinutes(Date date1, Date date2, int rangeInMinutes) {
    int rangeInSeconds = rangeInMinutes * 60;
    return isInRangeSeconds(date1, date2, rangeInSeconds);
  }

  private static boolean isInRangeSeconds(Date date1, Date date2, int rangeInSeconds) {
    Date latestTime = addSeconds(date2, rangeInSeconds);
    Date earliestTime = addSeconds(date2, -rangeInSeconds);
    return date1.before(latestTime) && date1.after(earliestTime);
  }
}
