package ru.mdh.android.configuration;

public class Config {

    static int port;
    static String address;

    static String phoneNumber;
    static String pin;
    static String udid;
    static long contactCountInMasterCopy;

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Config.address = address;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        Config.phoneNumber = phoneNumber;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Config.port = port;
    }

    public static String getPin() {
        return pin;
    }

    public static void setPin(String pin) {
        Config.pin = pin;
    }

    public static String getUdid() {
        return udid;
    }

    public static void setUdid(String udid) {
        Config.udid = udid;
    }

    public static long getContactCountInMasterCopy() {
        return contactCountInMasterCopy;
    }

    public static void setContactCountInMasterCopy(long contactCountInMasterCopy) {
        Config.contactCountInMasterCopy = contactCountInMasterCopy;
    }
}
