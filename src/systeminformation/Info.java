/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systeminformation;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author furkanb
 */
public class Info {

    public List<Hdd> list = new ArrayList();
    Properties prop = System.getProperties();

    public Info() {
        setDisks();
    }

    public String getOsName() {
        return prop.getProperty("os.name");
    }

    public String getOsArch() {
        return prop.getProperty("os.arch");
    }

    public String getOsVersion() {
        return prop.getProperty("os.version");
    }

    public String getJavaVersion() {
        return prop.getProperty("java.version");
    }

    public String getAccountName() {
        return prop.getProperty("user.name") + " ' " + prop.getProperty("user.home") + " '";
    }

    public void setDisks() {
        File[] roots = File.listRoots();
        for (File root : roots) {
            Hdd h = new Hdd();
            if (root.getTotalSpace() != 0) {

                h.setPath(root.getAbsolutePath());

                h.setTotal((int) (root.getTotalSpace() / 1073741824));
                h.setUsed((int) ((root.getTotalSpace() - root.getFreeSpace()) / 1073741824));
                h.setFree((int) (root.getFreeSpace() / 1073741824));

                list.add(h);
            }
        }
    }

    public Hdd getDisk(String path) {
        Hdd h = new Hdd();
        for (int a = 0; a < list.size(); a++) {
            if (list.get(a).getPath().equals(path)) {
                h = list.get(a);
            }
        }
        return h;
    }

    public String getLocalIp() {
        String text = "";
        try {

            InetAddress ip = InetAddress.getLocalHost();
            text = ip.getHostAddress();

        } catch (UnknownHostException e) {

            e.printStackTrace();

        }
        return text;
    }

    public String getMacAddress() {
        String macaddress = "";
        InetAddress ip;
        try {

            ip = InetAddress.getLocalHost();

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            macaddress = sb.toString();

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (SocketException e) {

            e.printStackTrace();

        }
        return macaddress;

    }

}
