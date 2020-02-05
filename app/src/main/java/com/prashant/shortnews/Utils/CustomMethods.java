package com.prashant.shortnews.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

public class CustomMethods {
    Calendar calendar;
    @NonNull
    SimpleDateFormat _12hrFormat = new SimpleDateFormat("hh:mm a");
    @NonNull
    SimpleDateFormat _24hrFormat = new SimpleDateFormat("HH:mm");
    @NonNull
    SimpleDateFormat currentTimeStampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @NonNull
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    @NonNull
    SimpleDateFormat shortDateFormat = new SimpleDateFormat("dd-MM-yy");
    @NonNull
    SimpleDateFormat hourOfDayFormat = new SimpleDateFormat("HH");
    @NonNull
    SimpleDateFormat minutesFormat = new SimpleDateFormat("mm");
    @NonNull
    SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMMM-yy");

    Intent intent;

    String permissionType;

    @Nullable
    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";




    @Nullable
    public synchronized static String uniqueId(@NonNull Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.commit();
            }
        }
        return uniqueID;
    }

    public boolean isNetworkAvailable(@NonNull Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public boolean validateMobile(@NonNull String mobileNumber) {


        if (mobileNumber.length() < 10) {
            return false;
        } else {
            char firstDigit = mobileNumber.charAt(0);

            return firstDigit == '7' || firstDigit == '8' || firstDigit == '9';
        }
    }



    public boolean validatePincode(@NonNull String pinCode) {

        return pinCode.length() == 6;


    }


    public boolean validatePasscode(@NonNull String passcode) {
        return passcode.length() >= 4;
    }

    public boolean validateEmail(@NonNull String email) {
        Pattern emailRegexPattern = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
                        ")+");

        return emailRegexPattern.matcher(email).matches();
    }

    public boolean validateET(@NonNull String name) {
        return name.length() > 0;
    }

//    public int validateArea(@NonNull ArrayList<String> listLocation, @NonNull ArrayList<LocationModel> listLocationModel, @NonNull String locationName) {
//        int areaID = 0;
//
//        for (int i = 0; i < listLocation.size(); i++) {
//            if (listLocation.get(i).trim().equals(locationName.trim())) {
//                areaID = listLocationModel.get(i).getLocationID();
//            }
//        }
//
//        return areaID;
//    }

    public String getPermissionType(int permissionRequestCode) {
        switch (permissionRequestCode) {


//            case Constant.PERMISSION_ACCESS_LOCATION:
//                permissionType = Manifest.permission.ACCESS_FINE_LOCATION;
//                break;
//
//            case Constant.PERMISSION_CALL:
//                permissionType = Manifest.permission.CALL_PHONE;
//                break;

            default:
                permissionType = "";
                break;
        }

        return permissionType;
    }

    public boolean hasRequestedPermission(@NonNull Context context, int permissionRequestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionType = getPermissionType(permissionRequestCode);

            return context.checkCallingOrSelfPermission(permissionType) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    public String getCurrentTime() {
        return _12hrFormat.format(new Date());
    }

    public String getCurrentDate() {
        return dateFormat.format(new Date());
    }

    public String getCurrentTS() {
        return currentTimeStampFormat.format(new Date());
    }

    @NonNull


    public String getUUID() {
        return String.valueOf(UUID.randomUUID());
    }

//    public void callNumber(@NonNull Context context, String mobileNumber) {
//        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
//            intent = new Intent(Intent.ACTION_CALL);
//            intent.setData(Uri.parse("tel:" + mobileNumber));
//            context.startActivity(intent);
//        } else {
//            Toast.makeText(context, R.string.call_feature_missing, Toast.LENGTH_LONG).show();
//        }
//    }
//
//    public void navigateUser(@NonNull Context context, double latitude, double longitude) {
//        String locationURI = String.format(Locale.ENGLISH, Constant.googleMapsURL + latitude + "," + longitude);
//        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(locationURI));
//        context.startActivity(intent);
//    }

//    public String userDonationStatus(@NonNull Context context, String insertedDate) {
//        String canDonate = "";
//        calendar = Calendar.getInstance();
//
//        try {
//            Date currentDate = dateFormat.parse(dateFormat.format(new Date()));
//            Date selectedDate = dateFormat.parse(insertedDate);
//
//            calendar.setTime(selectedDate);
//            calendar.add(Calendar.DATE, 90);
//            selectedDate = calendar.getTime();
//
//            if (selectedDate.before(currentDate)) {
//                canDonate = context.getString(R.string.user_can_donate_blood);
//            } else {
//                canDonate = dateFormat.format(selectedDate);
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return canDonate;
//    }

    public String convertTime(String timeString) {
        String _12hrString = "";

        try {
            Date date = _24hrFormat.parse(timeString);
            _12hrString = _12hrFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return _12hrString;
    }

//    @NonNull
//    public ArrayList<String> generateHourSlot(@NonNull Context context, @NonNull String sessionTime) {
//        calendar = Calendar.getInstance();
//        ArrayList<String> listHourSlots = new ArrayList<>();
//
//        String[] timings = sessionTime.split(" - ");
//
//        try {
//            Date startTime = _12hrFormat.parse(timings[0]);
//            Date endTime = _12hrFormat.parse(timings[1]);
//
//            listHourSlots.add(context.getString(R.string.please_select));
//
//            while (startTime.before(endTime)) {
//                calendar.setTime(startTime);
//                calendar.add(Calendar.MINUTE, 60);
//
//                listHourSlots.add(_12hrFormat.format(_24hrFormat.parse(_24hrFormat.format(startTime))) + " - " + _12hrFormat.format(_24hrFormat.parse(_24hrFormat.format(calendar.getTime()))));
//
//                startTime = calendar.getTime();
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return listHourSlots;
//    }
//

//    @NonNull
//    public ArrayList<String> generateThreeHourSlot(@NonNull Context context, @NonNull String sessionTime) {
//        calendar = Calendar.getInstance();
//        ArrayList<String> listHourSlots = new ArrayList<>();
//
//        String[] timings = sessionTime.split(" - ");
//
//        try {
//            Date startTime = _12hrFormat.parse(timings[0]);
//            Date endTime = _12hrFormat.parse(timings[1]);
//
//            listHourSlots.add(context.getString(R.string.please_select));
//
//            while (startTime.before(endTime)) {
//                calendar.setTime(startTime);
//                calendar.add(Calendar.MINUTE, 180);
//
//                listHourSlots.add(_12hrFormat.format(_24hrFormat.parse(_24hrFormat.format(startTime))) + " - " + _12hrFormat.format(_24hrFormat.parse(_24hrFormat.format(calendar.getTime()))));
//
//                startTime = calendar.getTime();
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return listHourSlots;
//    }

    @NonNull
    public String[] getTimeSlot(String selectedSlotTime, int consultTime) {
        calendar = Calendar.getInstance();
        String[] timings = new String[2];

        try {
            Date date = _12hrFormat.parse(selectedSlotTime);

            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, consultTime);

            timings[0] = selectedSlotTime;
            timings[1] = _12hrFormat.format(_24hrFormat.parse(_24hrFormat.format(calendar.getTime())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timings;
    }

    public int getHoursMinutes(int actionID, String timeString) {
        try {
            Date date = _12hrFormat.parse(timeString);

            switch (actionID) {
                case 1:
                    timeString = hourOfDayFormat.format(date);
                    break;

                case 2:
                    timeString = minutesFormat.format(date);
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(timeString);
    }

    public boolean isTimeBetweenTimeFrame(String appointmentTime, String startTime, String endTime) {
        boolean timeValid = false;
        calendar = Calendar.getInstance();

        try {
            Date apptTime = _12hrFormat.parse(appointmentTime);
            Date apptStartTime = _12hrFormat.parse(startTime);
            Date apptEndTime = _12hrFormat.parse(endTime);

            if ((apptStartTime.compareTo(apptTime) == 0 || apptStartTime.compareTo(apptTime) < 0)
                    && (apptTime.compareTo(apptEndTime) == 0 || apptTime.compareTo(apptEndTime) < 0)) {
                timeValid = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timeValid;
    }

    public int getAge(@NonNull String birthDate) {
        String[] dateParams = birthDate.split("-");

        Calendar dateOfBirth = Calendar.getInstance();
        calendar = Calendar.getInstance();

        dateOfBirth.set(Integer.parseInt(dateParams[2]), Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[0]));

        int actualAge = calendar.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        if (calendar.get(Calendar.DAY_OF_YEAR) < dateOfBirth.get(Calendar.DAY_OF_YEAR)) {
            actualAge--;
        }

        return actualAge;
    }


    @NonNull
    public String getDisplayMonth(String startDate) {
        String displayMonth = "";

        try {
            Date date = shortDateFormat.parse(startDate);
            displayMonth = monthYearFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return displayMonth.replace("-", " '");
    }

    public boolean getApptEligibility(String apptDate, String apptStartTime) {
        boolean eligible = false;
        calendar = Calendar.getInstance();

        try {
            Date appointmentDate = dateFormat.parse(apptDate);
            Date currentDate = dateFormat.parse(getCurrentDate());
            Date currentTime = _12hrFormat.parse(getCurrentTime());
            Date apptStart = _12hrFormat.parse(apptStartTime);

            int dateCompare = currentDate.compareTo(appointmentDate);

            if (dateCompare < 0) {
                eligible = true;
            } else if (dateCompare == 0) {
                calendar.setTime(currentTime);
                calendar.add(Calendar.MINUTE, 180);

                if (calendar.getTime().before(apptStart)) {
                    eligible = true;
                }
            } else {
                eligible = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return eligible;
    }

    public boolean getApptActionValidity(String apptDate, String apptStartTime) {
        boolean eligible = false;

        try {
            Date appointmentDate = dateFormat.parse(apptDate);
            Date currentDate = dateFormat.parse(getCurrentDate());
            Date currentTime = _12hrFormat.parse(getCurrentTime());
            Date apptStart = _12hrFormat.parse(apptStartTime);

            int dateCompare = currentDate.compareTo(appointmentDate);

            if (dateCompare < 0) {
                eligible = true;
            } else if (dateCompare == 0) {
                if (currentTime.before(apptStart)) {
                    eligible = true;
                }
            } else {
                eligible = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return eligible;
    }
}

